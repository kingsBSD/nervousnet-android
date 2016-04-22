package ch.ethz.soms.nervous.android.sensors;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by grg on 22/04/16.
 */
public class TrafficSensor {

    private Context context;
    private Handler scanHandle;
    private Runnable trafficWorker;

    ArrayList<Integer> uids;
    ConcurrentHashMap<Integer,String> namesByUid;
    ConcurrentHashMap<Integer,Long> trafficTxByUid;
    ConcurrentHashMap<Integer,Long> trafficRxByUid;

    public TrafficSensor(Context context) {
        this.context = context;
        scanHandle = new Handler();
        uids = new ArrayList<Integer>();
        namesByUid = new ConcurrentHashMap<Integer, String>();
        trafficTxByUid = new ConcurrentHashMap<Integer, Long>();
        trafficRxByUid = new ConcurrentHashMap<Integer, Long>();

        PackageManager pm = this.context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        String[] permissions;
        long txBytes, rxBytes;
        for (ApplicationInfo appInfo : packages) {
            try {
                permissions = pm.getPackageInfo(appInfo.packageName, PackageManager.GET_PERMISSIONS).requestedPermissions;
                if (permissions != null) {
                    for (String permission : permissions) {
                        if (permission.equals("android.permission.INTERNET")) {
                            // ...then get the initial traffic stats.
                            txBytes = TrafficStats.getUidTxBytes(appInfo.uid);
                            rxBytes = TrafficStats.getUidRxBytes(appInfo.uid);
                            if (txBytes != -1 && rxBytes != -1) {
                                uids.add(appInfo.uid);
                                namesByUid.put(appInfo.uid, appInfo.processName);
                                trafficTxByUid.put(appInfo.uid, txBytes);
                                trafficRxByUid.put(appInfo.uid, rxBytes);
                            }
                            break;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }

    }

    private Lock listenerMutex = new ReentrantLock();

    private List<TrafficListener> listenerList = new ArrayList<TrafficListener>();

    public interface TrafficListener {
        void trafficSensorDataReady(long timestamp, String appName, long bytesIn, long bytesOut);
    }

    public void addListener(TrafficListener listener) {
        listenerMutex.lock();
        listenerList.add(listener);
        listenerMutex.unlock();
    }

    public void removeListener(TrafficListener listener) {
        listenerMutex.lock();
        listenerList.remove(listener);
        listenerMutex.unlock();
    }

    public void clearListeners() {
        listenerMutex.lock();
        listenerList.clear();
        listenerMutex.unlock();
    }

    public void dataReady(long timestamp, String appName, long bytesIn, long bytesOut ) {
        listenerMutex.lock();
        for (TrafficListener listener : listenerList) {
            listener.trafficSensorDataReady(timestamp, appName, bytesIn, bytesOut);
        }
        listenerMutex.unlock();
    }

    public void start() {
        Log.d("traffic", "Traffic sensor started");
        new TrafficTask().execute();
    }

    public class TrafficTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("traffic", "scanning...");
            long bytes, txBytes, rxBytes, lastTx, lastRx = 0;
            for (Integer uid : uids) {
                txBytes = 0;
                rxBytes = 0;
                bytes = TrafficStats.getUidTxBytes(uid);
                lastTx =  trafficTxByUid.get(uid);
                lastRx =  trafficRxByUid.get(uid);
                if (bytes > trafficTxByUid.get(uid)) {
                    txBytes = bytes - lastTx;
                    trafficTxByUid.put(uid, bytes);
                }
                bytes = TrafficStats.getUidRxBytes(uid) - trafficTxByUid.get(uid);
                if (bytes > trafficRxByUid.get(uid)) {
                    rxBytes = bytes - lastRx;
                    trafficRxByUid.put(uid, bytes);
                }
                if (txBytes > 0 || rxBytes > 0) {
                    Log.d("traffic", "Sent data...");
                    dataReady(System.currentTimeMillis(), namesByUid.get(uid), rxBytes, txBytes);
                }
            }
            return null;
        }
    }

}
