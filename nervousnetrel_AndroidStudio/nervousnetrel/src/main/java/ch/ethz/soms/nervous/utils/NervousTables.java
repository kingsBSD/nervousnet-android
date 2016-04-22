package ch.ethz.soms.nervous.utils;

/**
 * Created by grg on 14/04/16.
 */

import android.provider.BaseColumns;

public class NervousTables {

    public NervousTables() {}

    static final String INTEGER_PRIMARY_KEY = "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";

    public static abstract class NervousTable implements BaseColumns {
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    }

    public static abstract class AccelerometerTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "accelerometer";
        public static final String COLUMN_NAME_ACCELEROMETER_X = "x";
        public static final String COLUMN_NAME_ACCELEROMETER_Y = "y";
        public static final String COLUMN_NAME_ACCELEROMETER_Z = "z";
    }

    static final String CREATE_ACCELEROMETER_TABLE =
            "CREATE TABLE " + AccelerometerTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    AccelerometerTable.COLUMN_NAME_ACCELEROMETER_X + " REAL, " +
                    AccelerometerTable.COLUMN_NAME_ACCELEROMETER_Y + " REAL, " +
                    AccelerometerTable.COLUMN_NAME_ACCELEROMETER_Z + " REAL, " +
                    AccelerometerTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class BatteryTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "battery";
        public static final String COLUMN_NAME_BATTERY_PERCENT = "percent";
        public static final String COLUMN_NAME_BATTERY_IS_CHARGING = "ischarging";
        public static final String COLUMN_NAME_BATTERY_IS_USB_CHARGE = "usbcharge";
        public static final String COLUMN_NAME_BATTERY_IS_AC_CHARGE = "accharge";
    }

    static final String CREATE_BATTERY_TABLE =
            "CREATE TABLE " + BatteryTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    BatteryTable.COLUMN_NAME_BATTERY_PERCENT + " REAL, " +
                    BatteryTable.COLUMN_NAME_BATTERY_IS_CHARGING + " INTEGER, " +
                    BatteryTable.COLUMN_NAME_BATTERY_IS_USB_CHARGE + " INTEGER, " +
                    BatteryTable.COLUMN_NAME_BATTERY_IS_AC_CHARGE + " INTEGER, " +
                    BatteryTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class BLEBeaconTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "blebeacon";
        public static final String COLUMN_NAME_BLEBEACON_MAC = "mac";
        public static final String COLUMN_NAME_BLEBEACON_ADVERTISEMENT_MSB = "advertisementmsb";
        public static final String COLUMN_NAME_BLEBEACON_ADVERTISEMENT_LSB = "advertisementlsb";
        public static final String COLUMN_NAME_BLEBEACON_BLEUUID_MSB = "bleuuidmsb";
        public static final String COLUMN_NAME_BLEBEACON_BLEUUID_LSB = "bleuuidlsb";
        public static final String COLUMN_NAME_BLEBEACON_RSSI = "rssi";
        public static final String COLUMN_NAME_BLEBEACON_MAJOR = "major";
        public static final String COLUMN_NAME_BLEBEACON_MINOR = "minor";
        public static final String COLUMN_NAME_BLEBEACON_TXPOWER = "txpower";
    }

    static final String CREATE_BLEBEACON_TABLE =
            "CREATE TABLE " + BLEBeaconTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_MAC + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_ADVERTISEMENT_MSB + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_ADVERTISEMENT_LSB + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_BLEUUID_MSB + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_BLEUUID_LSB + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_RSSI + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_MAJOR + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_MINOR + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_BLEBEACON_TXPOWER + " INTEGER, " +
                    BLEBeaconTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class ConnectivityTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "connectivity";
        public static final String COLUMN_NAME_CONNECTIVITY_NETWORK_TYPE = "networktype";
        public static final String COLUMN_NAME_CONNECTIVITY_IS_ROAMING = "isroaming";
        public static final String COLUMN_NAME_CONNECTIVITY_WIFI_HASH_ID = "wifihashid";
        public static final String COLUMN_NAME_CONNECTIVITY_WIFI_STRENGTH = "wifistrength";
        public static final String COLUMN_NAME_CONNECTIVITY_MOBILE_HASH_ID = "mobilehashid";
    }

    static final String CREATE_CONNECTIVITY_TABLE =
            "CREATE TABLE " + ConnectivityTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    ConnectivityTable.COLUMN_NAME_CONNECTIVITY_NETWORK_TYPE + " INTEGER, " +
                    ConnectivityTable.COLUMN_NAME_CONNECTIVITY_IS_ROAMING + " INTEGER, " +
                    ConnectivityTable.COLUMN_NAME_CONNECTIVITY_WIFI_HASH_ID + " TEXT, " +
                    ConnectivityTable.COLUMN_NAME_CONNECTIVITY_WIFI_STRENGTH + " INTEGER, " +
                    ConnectivityTable.COLUMN_NAME_CONNECTIVITY_MOBILE_HASH_ID + " TEXT, " +
                    ConnectivityTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class GyroscopeTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "gyroscope";
        public static final String COLUMN_NAME_GYROSCOPE_X = "x";
        public static final String COLUMN_NAME_GYROSCOPE_Y = "y";
        public static final String COLUMN_NAME_GYROSCOPE_Z = "z";
    }

    static final String CREATE_GYROSCOPE_TABLE =
            "CREATE TABLE " + GyroscopeTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    GyroscopeTable.COLUMN_NAME_GYROSCOPE_X + " REAL, " +
                    GyroscopeTable.COLUMN_NAME_GYROSCOPE_Y + " REAL, " +
                    GyroscopeTable.COLUMN_NAME_GYROSCOPE_Z + " REAL, " +
                    GyroscopeTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class HumidityTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "humidity";
        public static final String COLUMN_NAME_HUMIDITY = "humidity";
    }

    static final String CREATE_HUMIDITY_TABLE =
            "CREATE TABLE " + HumidityTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    HumidityTable.COLUMN_NAME_HUMIDITY + " REAL, " +
                    HumidityTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class LightTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "light";
        public static final String COLUMN_NAME_LIGHT = "light";
    }

    static final String CREATE_LIGHT_TABLE =
            "CREATE TABLE " + LightTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    LightTable.COLUMN_NAME_LIGHT + " REAL, " +
                    LightTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class MagneticTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "magnetic";
        public static final String COLUMN_NAME_MAGNETIC_X = "x";
        public static final String COLUMN_NAME_MAGNETIC_Y = "y";
        public static final String COLUMN_NAME_MAGNETIC_Z = "z";
    }

    static final String CREATE_MAGNETIC_TABLE =
            "CREATE TABLE " + MagneticTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    MagneticTable.COLUMN_NAME_MAGNETIC_X + " REAL, " +
                    MagneticTable.COLUMN_NAME_MAGNETIC_Y + " REAL, " +
                    MagneticTable.COLUMN_NAME_MAGNETIC_Z + " REAL, " +
                    MagneticTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class NoiseTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "noise";
        public static final String COLUMN_NAME_NOISE_RMS = "rms";
        public static final String COLUMN_NAME_NOISE_SPL = "spl";
        public static final String COLUMN_NAME_NOISE_BANDS = "bands";
    }

    static final String CREATE_NOISE_TABLE =
            "CREATE TABLE " + NoiseTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    NoiseTable.COLUMN_NAME_NOISE_RMS + " REAL, " +
                    NoiseTable.COLUMN_NAME_NOISE_SPL + " REAL, " +
                    NoiseTable.COLUMN_NAME_NOISE_BANDS + " TEXT, " +
                    NoiseTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class NotificationTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "notification";
        public static final String COLUMN_NAME_NOTIFICATION_APP_NAME = "appname";
    }

    static final String CREATE_NOTIFICATION_TABLE =
            "CREATE TABLE " + NotificationTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    NotificationTable.COLUMN_NAME_NOTIFICATION_APP_NAME + " TEXT, " +
                    NotificationTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class PressureTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "pressure";
        public static final String COLUMN_NAME_PRESSURE = "pressure";
    }

    static final String CREATE_PRESSURE_TABLE =
            "CREATE TABLE " + PressureTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    PressureTable.COLUMN_NAME_PRESSURE + " REAL, " +
                    PressureTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class ProximityTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "proximity";
        public static final String COLUMN_NAME_PROXIMITY = "proximity";
    }

    static final String CREATE_PROXIMITY_TABLE =
            "CREATE TABLE " + ProximityTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    ProximityTable.COLUMN_NAME_PROXIMITY + " REAL, " +
                    ProximityTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class SocketTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "socket";
        public static final String COLUMN_NAME_SOCKET_APP_NAME = "appname";
        public static final String COLUMN_NAME_SOCKET_PROTOCOL = "protocol";
        public static final String COLUMN_NAME_SOCKET_PORT = "port";
    }

    static final String CREATE_SOCKET_TABLE =
            "CREATE TABLE " + SocketTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    SocketTable.COLUMN_NAME_SOCKET_APP_NAME + " TEXT, " +
                    SocketTable.COLUMN_NAME_SOCKET_PROTOCOL + " TEXT, " +
                    SocketTable.COLUMN_NAME_SOCKET_PORT + " INTEGER, " +
                    SocketTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class TemperatureTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "temperature";
        public static final String COLUMN_NAME_TEMPERATURE = "temperature";
    }

    static final String CREATE_TEMPERATURE_TABLE =
            "CREATE TABLE " + TemperatureTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    TemperatureTable.COLUMN_NAME_TEMPERATURE + " REAL, " +
                    TemperatureTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static abstract class TrafficTable extends NervousTable implements BaseColumns {
        public static final String TABLE_NAME = "traffic";
        public static final String COLUMN_NAME_TRAFFIC_APP_NAME = "appname";
        public static final String COLUMN_NAME_TRAFFIC_BYTES_IN = "bytesin";
        public static final String COLUMN_NAME_TRAFFIC_BYTES_OUT = "bytesout";
    }

    static final String CREATE_TRAFFIC_TABLE =
            "CREATE TABLE " + TrafficTable.TABLE_NAME + " (" +
                    INTEGER_PRIMARY_KEY +
                    TrafficTable.COLUMN_NAME_TRAFFIC_APP_NAME + " TEXT, " +
                    TrafficTable.COLUMN_NAME_TRAFFIC_BYTES_IN + " INTEGER, " +
                    TrafficTable.COLUMN_NAME_TRAFFIC_BYTES_OUT + " INTEGER, " +
                    TrafficTable.COLUMN_NAME_TIMESTAMP + " INTEGER );";

    public static final String[] CreateTables = {
            CREATE_ACCELEROMETER_TABLE,
            CREATE_BATTERY_TABLE,
            CREATE_BLEBEACON_TABLE,
            CREATE_CONNECTIVITY_TABLE,
            CREATE_GYROSCOPE_TABLE,
            CREATE_HUMIDITY_TABLE,
            CREATE_LIGHT_TABLE,
            CREATE_MAGNETIC_TABLE,
            CREATE_NOISE_TABLE,
            CREATE_NOTIFICATION_TABLE,
            CREATE_PRESSURE_TABLE,
            CREATE_PROXIMITY_TABLE,
            CREATE_SOCKET_TABLE,
            CREATE_TEMPERATURE_TABLE,
            CREATE_TRAFFIC_TABLE
    };

}
