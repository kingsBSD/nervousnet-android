package ch.ethz.soms.nervous.android.sensors;

import android.app.ActivityManager;
import android.content.Context;
import android.os.AsyncTask;
//import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by grg on 22/04/16.
 */
public class SocketSensor {

    private String[] protocols = new String[]{"tcp", "tcp6", "udp", "udp6"};
    private ActivityManager am;

    public SocketSensor(Context ctx) {
        am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
    }

    private Lock listenerMutex = new ReentrantLock();

    private List<SocketListener> listenerList = new ArrayList<SocketListener>();

    public interface SocketListener {
        void socketSensorDataReady(long timestamp, String appName, String protocol, int port);
    }

    public void addListener(SocketListener listener) {
        listenerMutex.lock();
        listenerList.add(listener);
        listenerMutex.unlock();
    }

    public void removeListener(SocketListener listener) {
        listenerMutex.lock();
        listenerList.remove(listener);
        listenerMutex.unlock();
    }

    public void clearListeners() {
        listenerMutex.lock();
        listenerList.clear();
        listenerMutex.unlock();
    }

    public void dataReady(long timestamp, String appName, String protocol, int port) {
        listenerMutex.lock();
        for (SocketListener listener : listenerList) {
                listener.socketSensorDataReady(timestamp, appName, protocol, port);
        }
        listenerMutex.unlock();
    }

    public void start() {
        //Log.d("socket", "socket sensor started");
        new TrafficTask().execute();
    }

    public class TrafficTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            List<ActivityManager.RunningAppProcessInfo> procs = am.getRunningAppProcesses();

            ArrayList<String> pids = new ArrayList<String>(); // Process IDs.
            HashMap<String, String> namesByUid = new HashMap<String, String>();
            HashMap<String, String> pidsByUid = new HashMap<String, String>();
            String thisUid, thisPid, thisName;

            BufferedReader reader;

            String line;
            String[] tokens, remote;
            int remotePort;

            for (ActivityManager.RunningAppProcessInfo p : procs) {
                thisPid = Integer.toString(p.pid);
                pids.add(thisPid);
                thisUid = Integer.toString(p.uid);
                namesByUid.put(thisUid, p.processName);
                pidsByUid.put(thisUid, thisPid);
            }

            for (String prot : protocols) {
                //Log.d("socket", prot);
                for (String scannedPid : pids) { // For each process ID...

                    try {
                        // Are we allowed to read /proc/<pid>/net/<protocol> ?
                        reader = new BufferedReader(new FileReader("/proc/" + scannedPid + "/net/" + prot));

                        while ((line = reader.readLine()) != null) {
                            tokens = line.split("\\s+"); // Split on whitespace...
                            remote = tokens[3].split(":"); // Maybe the fourth token is the remote address...

                            if (remote.length > 1) { // ...if there are two tokens separated by a ":".
                                thisUid = tokens[8];
                                thisPid = pidsByUid.get(thisUid);
                                thisName = namesByUid.get(thisUid);

                                if (thisName != null && thisPid != null) {
                                    remotePort = Integer.valueOf(remote[1], 16); // Get the port from the HEX string.
                                    dataReady(System.currentTimeMillis(), thisName, prot, remotePort);
                                    //Log.d("socket", "Transmitted socket data.");
                                }
                            }
                        }
                    } catch (IOException e) {
                        //Log.d("socket", "Can't open /proc/<pid>/net");
                    }
                }
            }

            return null;
        }

    }
}
