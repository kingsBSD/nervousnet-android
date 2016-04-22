package ch.ethz.soms.nervous.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import ch.ethz.soms.nervous.android.sensors.SensorDescAccelerometerNew;
import ch.ethz.soms.nervous.android.sensors.SensorDescBattery;
import ch.ethz.soms.nervous.android.sensors.SensorDescBLEBeacon;
import ch.ethz.soms.nervous.android.sensors.SensorDescConnectivity;
import ch.ethz.soms.nervous.android.sensors.SensorDescGyroscopeNew;
import ch.ethz.soms.nervous.android.sensors.SensorDescHumidity;
import ch.ethz.soms.nervous.android.sensors.SensorDescLight;
import ch.ethz.soms.nervous.android.sensors.SensorDescMagneticNew;
import ch.ethz.soms.nervous.android.sensors.SensorDescNoise;
import ch.ethz.soms.nervous.android.sensors.SensorDescNotification;
import ch.ethz.soms.nervous.android.sensors.SensorDescPressure;
import ch.ethz.soms.nervous.android.sensors.SensorDescProximity;
import ch.ethz.soms.nervous.android.sensors.SensorDescSocket;
import ch.ethz.soms.nervous.android.sensors.SensorDescTemperature;

import ch.ethz.soms.nervous.android.sensors.SensorDescTraffic;
import ch.ethz.soms.nervous.utils.NervousTables.AccelerometerTable;
import ch.ethz.soms.nervous.utils.NervousTables.BatteryTable;
import ch.ethz.soms.nervous.utils.NervousTables.BLEBeaconTable;
import ch.ethz.soms.nervous.utils.NervousTables.ConnectivityTable;
import ch.ethz.soms.nervous.utils.NervousTables.GyroscopeTable;
import ch.ethz.soms.nervous.utils.NervousTables.HumidityTable;
import ch.ethz.soms.nervous.utils.NervousTables.LightTable;
import ch.ethz.soms.nervous.utils.NervousTables.MagneticTable;
import ch.ethz.soms.nervous.utils.NervousTables.NoiseTable;
import ch.ethz.soms.nervous.utils.NervousTables.NotificationTable;
import ch.ethz.soms.nervous.utils.NervousTables.PressureTable;
import ch.ethz.soms.nervous.utils.NervousTables.ProximityTable;
import ch.ethz.soms.nervous.utils.NervousTables.TemperatureTable;

public class NervousData extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "NervousNet.db";

	public NervousData(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public NervousData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		for (String sql: NervousTables.CreateTables)
		{
			try {
				db.execSQL(sql);
			}
			catch (Exception e) {
				//Log.i("NervousData",sql);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	public void putAccelerometerData(SQLiteDatabase db, SensorDescAccelerometerNew accelerometer) {
		ContentValues values = new ContentValues();
		values.put(AccelerometerTable.COLUMN_NAME_ACCELEROMETER_X, accelerometer.getAccX());
		values.put(AccelerometerTable.COLUMN_NAME_ACCELEROMETER_Y, accelerometer.getAccY());
		values.put(AccelerometerTable.COLUMN_NAME_ACCELEROMETER_Z, accelerometer.getAccZ());
		values.put(AccelerometerTable.COLUMN_NAME_TIMESTAMP,accelerometer.getTimestamp());
		putRow(db,AccelerometerTable.TABLE_NAME,values);
	}

	public void putBatteryData(SQLiteDatabase db, SensorDescBattery battery) {
		ContentValues values = new ContentValues();
		values.put(BatteryTable.COLUMN_NAME_BATTERY_PERCENT, battery.getBatteryPercent());
		putBoolean(values, BatteryTable.COLUMN_NAME_BATTERY_IS_CHARGING, battery.isCharging());
		putBoolean(values, BatteryTable.COLUMN_NAME_BATTERY_IS_USB_CHARGE, battery.isUsbCharge());
		putBoolean(values, BatteryTable.COLUMN_NAME_BATTERY_IS_AC_CHARGE, battery.isAcCharge());
		values.put(BatteryTable.COLUMN_NAME_TIMESTAMP, battery.getTimestamp());
		putRow(db,BatteryTable.TABLE_NAME,values);
	}

	public void putBLEBeaconData(SQLiteDatabase db, SensorDescBLEBeacon beacon) {
		ContentValues values = new ContentValues();
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_MAC, beacon.getMac());
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_ADVERTISEMENT_MSB, beacon.getAdvertisementMSB());
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_ADVERTISEMENT_LSB, beacon.getAdvertisementLSB());
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_BLEUUID_MSB, beacon.getBleuuidMSB());
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_BLEUUID_LSB, beacon.getBleuuidLSB());
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_RSSI, beacon.getRssi());
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_MAJOR, beacon.getMajor());
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_MINOR, beacon.getMinor());
		values.put(BLEBeaconTable.COLUMN_NAME_BLEBEACON_TXPOWER, beacon.getTxpower());
		values.put(BLEBeaconTable.COLUMN_NAME_TIMESTAMP, beacon.getTimestamp());
		putRow(db,BLEBeaconTable.TABLE_NAME,values);
	}

	public void putConectivityData(SQLiteDatabase db, SensorDescConnectivity connect) {
		ContentValues values = new ContentValues();
		values.put(ConnectivityTable.COLUMN_NAME_CONNECTIVITY_NETWORK_TYPE, connect.getNetworkType());
		putBoolean(values, ConnectivityTable.COLUMN_NAME_CONNECTIVITY_IS_ROAMING, connect.isRoaming());
		values.put(ConnectivityTable.COLUMN_NAME_CONNECTIVITY_WIFI_HASH_ID, connect.getWifiHashId());
		values.put(ConnectivityTable.COLUMN_NAME_CONNECTIVITY_WIFI_STRENGTH, connect.getWifiStrength());
		values.put(ConnectivityTable.COLUMN_NAME_CONNECTIVITY_MOBILE_HASH_ID, connect.getMobileHashId());
		values.put(ConnectivityTable.COLUMN_NAME_TIMESTAMP, connect.getTimestamp());
		putRow(db, ConnectivityTable.TABLE_NAME, values);
	}

	public void putGyroscopeData(SQLiteDatabase db, SensorDescGyroscopeNew gyro) {
		ContentValues values = new ContentValues();
		values.put(GyroscopeTable.COLUMN_NAME_GYROSCOPE_X,gyro.getGyrX());
		values.put(GyroscopeTable.COLUMN_NAME_GYROSCOPE_Y, gyro.getGyrY());
		values.put(GyroscopeTable.COLUMN_NAME_GYROSCOPE_Z,gyro.getGyrZ());
		values.put(GyroscopeTable.COLUMN_NAME_TIMESTAMP, gyro.getTimestamp());
		putRow(db, GyroscopeTable.TABLE_NAME, values);
	}

	public void putHumidityData(SQLiteDatabase db, SensorDescHumidity humidity) {
		ContentValues values = new ContentValues();
		values.put(HumidityTable.COLUMN_NAME_HUMIDITY,humidity.getHumidity());
		values.put(HumidityTable.COLUMN_NAME_TIMESTAMP, humidity.getTimestamp());
		putRow(db, HumidityTable.TABLE_NAME, values);
	}

	public void putLightData(SQLiteDatabase db, SensorDescLight light) {
		ContentValues values = new ContentValues();
		values.put(LightTable.COLUMN_NAME_LIGHT, light.getLight());
		values.put(LightTable.COLUMN_NAME_TIMESTAMP, light.getTimestamp());
		putRow(db, LightTable.TABLE_NAME, values);
	}

	public void putMagneticData(SQLiteDatabase db, SensorDescMagneticNew magnet) {
		ContentValues values = new ContentValues();
		values.put(MagneticTable.COLUMN_NAME_MAGNETIC_X,magnet.getMagX());
		values.put(MagneticTable.COLUMN_NAME_MAGNETIC_Y,magnet.getMagY());
		values.put(MagneticTable.COLUMN_NAME_MAGNETIC_Z,magnet.getMagZ());
		values.put(MagneticTable.COLUMN_NAME_TIMESTAMP, magnet.getTimestamp());
		putRow(db, MagneticTable.TABLE_NAME, values);
	}

	public void putNoiseData(SQLiteDatabase db, SensorDescNoise noise) {
		ContentValues values = new ContentValues();
		values.put(NoiseTable.COLUMN_NAME_NOISE_RMS,noise.getRms());
		values.put(NoiseTable.COLUMN_NAME_NOISE_SPL, noise.getSpl());
		List<String> bandStrings = new ArrayList<String>();
		try {
			for (float band : noise.getBands()) {
				bandStrings.add(Float.toString(band));
			}
			values.put(NoiseTable.COLUMN_NAME_NOISE_BANDS, TextUtils.join(" ", bandStrings));
		} catch (java.lang.NullPointerException e) {
			// Left as null.
		}
		values.put( NoiseTable.COLUMN_NAME_TIMESTAMP, noise.getTimestamp());
		putRow(db, NoiseTable.TABLE_NAME, values);
	}

	public void putNotificationData(SQLiteDatabase db, SensorDescNotification notification) {
		ContentValues values = new ContentValues();
		values.put(NotificationTable.COLUMN_NAME_NOTIFICATION_APP_NAME, notification.getAppName());
		values.put(NotificationTable.COLUMN_NAME_TIMESTAMP, notification.getTimestamp());
		putRow(db, NotificationTable.TABLE_NAME, values);
	}

	public void putPressureData(SQLiteDatabase db, SensorDescPressure pressure) {
		ContentValues values = new ContentValues();
		values.put(PressureTable.COLUMN_NAME_PRESSURE, pressure.getPressure());
		values.put(PressureTable.COLUMN_NAME_TIMESTAMP, pressure.getTimestamp());
		putRow(db, PressureTable.TABLE_NAME, values);
	}

	public void putProximityData(SQLiteDatabase db, SensorDescProximity proximity) {
		ContentValues values = new ContentValues();
		values.put(ProximityTable.COLUMN_NAME_PROXIMITY, proximity.getProximity());
		values.put(ProximityTable.COLUMN_NAME_TIMESTAMP, proximity.getTimestamp());
		putRow(db, ProximityTable.TABLE_NAME, values);
	}

	public void putSocketData(SQLiteDatabase db, SensorDescSocket socket) {
		ContentValues values = new ContentValues();
		values.put(NervousTables.SocketTable.COLUMN_NAME_SOCKET_APP_NAME, socket.getAppName());
		values.put(NervousTables.SocketTable.COLUMN_NAME_SOCKET_PROTOCOL, socket.getProtocol());
		values.put(NervousTables.SocketTable.COLUMN_NAME_SOCKET_PORT, socket.getPort());
		values.put(NervousTables.SocketTable.COLUMN_NAME_TIMESTAMP, socket.getTimestamp());
		putRow(db, NervousTables.SocketTable.TABLE_NAME, values);
	}

	public void putTemperatureData(SQLiteDatabase db, SensorDescTemperature temperature) {
		ContentValues values = new ContentValues();
		values.put(TemperatureTable.COLUMN_NAME_TEMPERATURE, temperature.getTemperature());
		values.put(TemperatureTable.COLUMN_NAME_TIMESTAMP, temperature.getTimestamp());
		putRow(db, TemperatureTable.TABLE_NAME, values);
	}

	public void putTrafficData(SQLiteDatabase db, SensorDescTraffic traffic) {
		ContentValues values = new ContentValues();
		values.put(NervousTables.TrafficTable.COLUMN_NAME_TRAFFIC_APP_NAME, traffic.getAppName());
		values.put(NervousTables.TrafficTable.COLUMN_NAME_TRAFFIC_BYTES_IN, traffic.getbytesIn());
		values.put(NervousTables.TrafficTable.COLUMN_NAME_TRAFFIC_BYTES_OUT, traffic.getbytesOut());
		values.put(NervousTables.TrafficTable.COLUMN_NAME_TIMESTAMP, traffic.getTimestamp());
		putRow(db, NervousTables.TrafficTable.TABLE_NAME, values);
	}

	private void putBoolean(ContentValues values, String column, boolean bool) {
		int val = 0;
		if (bool) val = 1;
		values.put(column,val);
	}

	private void putRow(SQLiteDatabase db, String table, ContentValues values ) {
		// http://developer.android.com/training/basics/data-storage/databases.html#WriteDbRow
		try {
			db.insert(table,null,values);
		}
		catch(Exception e) {

		}
	}

}
