package ch.ethz.soms.nervous.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.Closeable;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.media.MediaScannerConnection;
import android.widget.Toast;

import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesAccelerometer;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesBLEBeacon;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesBattery;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesConnectivity;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesGyroscope;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesHumidity;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesLight;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesMagnetic
        ;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesNoise;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesNotification;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesPressure;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesProximity;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesTemperature;
import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesTraffic;
import ch.ethz.soms.nervous.android.sensors.SensorDescAccelerometerNew;
import ch.ethz.soms.nervous.android.sensors.SensorDescBLEBeacon;
import ch.ethz.soms.nervous.android.sensors.SensorDescBattery;
import ch.ethz.soms.nervous.android.sensors.SensorDescConnectivity;
import ch.ethz.soms.nervous.android.sensors.SensorDescGyroscopeNew;
import ch.ethz.soms.nervous.android.sensors.SensorDescHumidity;
import ch.ethz.soms.nervous.android.sensors.SensorDescLight;
import ch.ethz.soms.nervous.android.sensors.SensorDescMagneticNew;
import ch.ethz.soms.nervous.android.sensors.SensorDescNoise;
import ch.ethz.soms.nervous.android.sensors.SensorDescNotification;
import ch.ethz.soms.nervous.android.sensors.SensorDescPressure;
import ch.ethz.soms.nervous.android.sensors.SensorDescProximity;
import ch.ethz.soms.nervous.android.sensors.SensorDescTemperature;
import ch.ethz.soms.nervous.android.sensors.SensorDescTraffic;
import ch.ethz.soms.nervous.utils.NervousData;
import ch.ethz.soms.nervous.utils.NervousTables;
import ch.ethz.soms.nervousnet.R;

/**
 * Created by grg on 19/04/16.
 */
public class DbDumpTask extends AsyncTask<Integer, Integer, Integer> {
    // http://www.techrepublic.com/blog/software-engineer/export-sqlite-data-from-your-android-device/#.
    private Context context;
    private ProgressDialog progress;
    private long timeStamp;
    private NervousData helper;
    private SQLiteDatabase db;
    private String currentTable;

    public DbDumpTask(Context ctx) {
        context = ctx;
        helper = new NervousData(context);
    }

    @Override
    protected void onPreExecute() {

        timeStamp = System.currentTimeMillis();
        db = helper.getWritableDatabase();

        progress = new ProgressDialog(context);
        progress.setTitle("Exporting Data");
        progress.setMessage("...");
        progress.setCancelable(true);
        progress.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DbDumpTask.this.cancel(false);
            }
        });
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setProgress(0);
        progress.setMax(100);
        progress.show();
    }

    @Override
    protected Integer doInBackground(Integer... params) {

        currentTable = NervousTables.AccelerometerTable.TABLE_NAME;
        dumpAccelerometerData();
        currentTable = NervousTables.BatteryTable.TABLE_NAME;
        dumpBatteryData();
        currentTable = NervousTables.BLEBeaconTable.TABLE_NAME;;
        dumpBluetoothData();
        currentTable = NervousTables.ConnectivityTable.TABLE_NAME;;
        dumpConnectivityData();
        currentTable = NervousTables.GyroscopeTable.TABLE_NAME;;
        dumpGyroscopeData();
        currentTable = NervousTables.HumidityTable.TABLE_NAME;;
        dumpHumidityData();
        currentTable = NervousTables.LightTable.TABLE_NAME;
        dumpLightData();
        currentTable = NervousTables.MagneticTable.TABLE_NAME;;
        dumpMagneticData();
        currentTable = NervousTables.NoiseTable.TABLE_NAME;;
        dumpNoiseData();
        currentTable = NervousTables.NotificationTable.TABLE_NAME;
        dumpNotificationData();
        currentTable = NervousTables.PressureTable.TABLE_NAME;
        dumpPressureData();
        currentTable = NervousTables.ProximityTable.TABLE_NAME;
        dumpProximityData();
        currentTable = NervousTables.TemperatureTable.TABLE_NAME;
        dumpTemperatureData();
        currentTable = NervousTables.TrafficTable.TABLE_NAME;
        dumpTrafficData();

        return 0;
    }

    @Override
    protected void onPostExecute(Integer junk) {

        progress.dismiss();

        db.close();

        File source = Environment.getDataDirectory();
        File dest = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date rightNow = new Date();
        String exportPath = "NervousNet-" + df.format(rightNow) + ".sqlite";
        File exportFile = new File(dest, exportPath);

        File dbFile = context.getDatabasePath(helper.DATABASE_NAME);

        FileChannel sourceStream = null, destStream = null;

        try {
            sourceStream = new FileInputStream(dbFile).getChannel();
            destStream = new FileOutputStream(exportFile).getChannel();
            destStream.transferFrom(sourceStream, 0, sourceStream.size());
            Toast.makeText(context, "Data Exported to Downloads.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Couldn't Export Data!", Toast.LENGTH_LONG).show();
        } finally { // We don't want to be leaky
            closeResourceGracefully(sourceStream);
            closeResourceGracefully(destStream);
        }

        // http://stackoverflow.com/questions/13737261/nexus-4-not-showing-files-via-mtp
        MediaScannerConnection.scanFile(context, new String[]{exportFile.getAbsolutePath()}, null, null);

        context.deleteDatabase(helper.DATABASE_NAME);
    }

    @Override
    protected void onCancelled(Integer junk) {
        context.deleteDatabase(helper.DATABASE_NAME);
        progress.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progress.setProgress(values[0]);
        switch (currentTable) {
            case NervousTables.AccelerometerTable.TABLE_NAME: progress.setMessage("Exporting Accelerometer Data"); break;
            case NervousTables.BatteryTable.TABLE_NAME: progress.setMessage("Exporting Battery Data"); break;
            case NervousTables.BLEBeaconTable.TABLE_NAME: progress.setMessage("Exporting Bluetooth Data"); break;
            case NervousTables.ConnectivityTable.TABLE_NAME: progress.setMessage("Exporting Connectivity Data"); break;
            case NervousTables.GyroscopeTable.TABLE_NAME: progress.setMessage("Exporting Gyroscope Data"); break;
            case NervousTables.HumidityTable.TABLE_NAME: progress.setMessage("Exporting Humidity Data"); break;
            case NervousTables.LightTable.TABLE_NAME: progress.setMessage("Exporting Light Data"); break;
            case NervousTables.MagneticTable.TABLE_NAME: progress.setMessage("Exporting Magnetic Data"); break;
            case NervousTables.NoiseTable.TABLE_NAME: progress.setMessage("Exporting Noise Data"); break;
            case NervousTables.NotificationTable.TABLE_NAME: progress.setMessage("Exporting Notification Data"); break;
            case NervousTables.PressureTable.TABLE_NAME: progress.setMessage("Exporting Pressure Data"); break;
            case NervousTables.ProximityTable.TABLE_NAME: progress.setMessage("Exporting Proximity Data"); break;
            case NervousTables.TemperatureTable.TABLE_NAME: progress.setMessage("Exporting Temperature Data"); break;
            case NervousTables.TrafficTable.TABLE_NAME: progress.setMessage("Exporting Network Traffic Data"); break;
            default: progress.setMessage("...");
        }
    }

    private static void closeResourceGracefully(Closeable closeMe) {
        if (closeMe != null) {
            try {
                closeMe.close();
            } catch (IOException e) {
                // It was already closed, ignore.
            }
        }
    }

    private class Ticker {
        private int maxCount, ticks, percent;

        public Ticker(int count) {
            maxCount = count;
            percent = 0;
            publishProgress(percent);
        }

        private void tick() {
            ticks += 1;
            int update = (int) Math.round(maxCount * ticks / 100.0);
            if (update > percent) {
                percent = update;
                publishProgress(percent);
            }
        }

    }

    private void dumpAccelerometerData() {
        SensorQueriesAccelerometer sensorQuery = new SensorQueriesAccelerometer(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescAccelerometerNew> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescAccelerometerNew desc : sensorDescs) {
                helper.putAccelerometerData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpBatteryData() {
        SensorQueriesBattery sensorQuery = new SensorQueriesBattery(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescBattery> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescBattery desc : sensorDescs) {
                helper.putBatteryData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpBluetoothData() {
        SensorQueriesBLEBeacon sensorQuery = new SensorQueriesBLEBeacon(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescBLEBeacon> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescBLEBeacon desc: sensorDescs) {
                helper.putBLEBeaconData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpConnectivityData() {
        SensorQueriesConnectivity sensorQuery = new SensorQueriesConnectivity(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescConnectivity> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescConnectivity desc : sensorDescs) {
                helper.putConectivityData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpGyroscopeData() {
        SensorQueriesGyroscope sensorQuery = new SensorQueriesGyroscope(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescGyroscopeNew> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescGyroscopeNew desc: sensorDescs) {
                helper.putGyroscopeData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpHumidityData() {
        SensorQueriesHumidity sensorQuery = new SensorQueriesHumidity(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescHumidity> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescHumidity desc : sensorDescs) {
                helper.putHumidityData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpLightData() {
        SensorQueriesLight sensorQuery = new SensorQueriesLight(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescLight> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescLight desc : sensorDescs) {
                helper.putLightData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpMagneticData() {
        SensorQueriesMagnetic sensorQuery = new SensorQueriesMagnetic(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescMagneticNew> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescMagneticNew desc : sensorDescs) {
                helper.putMagneticData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpNoiseData() {
        SensorQueriesNoise sensorQuery = new SensorQueriesNoise(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescNoise> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescNoise desc : sensorDescs) {
                helper.putNoiseData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpNotificationData() {
        SensorQueriesNotification sensorQuery = new SensorQueriesNotification(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescNotification> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescNotification desc : sensorDescs) {
                helper.putNotificationData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpPressureData() {
        SensorQueriesPressure sensorQuery = new SensorQueriesPressure(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescPressure> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescPressure desc : sensorDescs) {
                helper.putPressureData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpProximityData() {
        SensorQueriesProximity sensorQuery = new SensorQueriesProximity(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescProximity> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescProximity desc : sensorDescs) {
                helper.putProximityData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpTemperatureData() {
        SensorQueriesTemperature sensorQuery = new SensorQueriesTemperature(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescTemperature> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescTemperature desc : sensorDescs) {
                helper.putTemperatureData(db, desc);
                ticker.tick();
            }
        }
    }

    private void dumpTrafficData() {
        SensorQueriesTraffic sensorQuery = new SensorQueriesTraffic(0, timeStamp, context.getFilesDir());
        if (sensorQuery.containsReadings()) {
            Ticker ticker = new Ticker(sensorQuery.getCount());
            ArrayList<SensorDescTraffic> sensorDescs = sensorQuery.getSensorDescriptorList();
            for (SensorDescTraffic desc : sensorDescs) {
                helper.putTrafficData(db, desc);
                ticker.tick();
            }
        }
    }
    
}
