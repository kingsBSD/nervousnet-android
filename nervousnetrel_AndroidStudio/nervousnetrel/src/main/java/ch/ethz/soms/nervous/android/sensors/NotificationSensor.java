package ch.ethz.soms.nervous.android.sensors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
//import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by grg on 08/04/16.
 */
public class NotificationSensor {

    private Context context;

    public NotificationSensor(Context context) {
        this.context = context;
    }

    private Lock listenerMutex = new ReentrantLock();

    private List<NotificationListener> listenerList = new ArrayList<NotificationListener>();

    public interface NotificationListener {
        void notificationSensorDataReady(long timestamp, String appName);
    }

    public void addListener(NotificationListener listener) {
        listenerMutex.lock();
        listenerList.add(listener);
        listenerMutex.unlock();
    }

    public void removeListener(NotificationListener listener) {
        listenerMutex.lock();
        listenerList.remove(listener);
        listenerMutex.unlock();
    }

    public void clearListeners() {
        listenerMutex.lock();
        listenerList.clear();
        listenerMutex.unlock();
    }

    public void dataReady(long timestamp, String appName ) {
        listenerMutex.lock();
        for (NotificationListener listener : listenerList) {
            listener.notificationSensorDataReady(timestamp, appName);
        }
        listenerMutex.unlock();
    }

    public void start() {
        //Log.d("sensor", "Notification Sensor Started");
        LocalBroadcastManager.getInstance(context).registerReceiver(
                notificationReceiver, new IntentFilter("nervousnet-notification-sensor-event"));
    }

    private BroadcastReceiver notificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            dataReady(intent.getLongExtra("timestamp",0L),intent.getStringExtra("appName"));
        }
    };

}
