package ch.ethz.soms.nervous.android.sensors;

import ch.ethz.soms.nervous.nervousproto.SensorUploadProtos.SensorUpload.SensorData;

/**
 * Created by grg on 21/04/16.
 */

public class SensorDescTraffic extends SensorDesc {

    public static final long SENSOR_ID = 0x0000000000000014L;

    private final String appName;
    private final long bytesIn;
    private final long bytesOut;

    public SensorDescTraffic(long timestamp, String name, long in, long out) {
        super(timestamp);
        appName = name;
        bytesIn = in;
        bytesOut = out;
    }

    public SensorDescTraffic(SensorData sensorData) {
        super(sensorData);
        appName = sensorData.getValueString(0);
        bytesIn = sensorData.getValueInt64(0);
        bytesOut = sensorData.getValueInt64(1);
    }

    public String getAppName() {return appName;}
    public long getbytesIn() {return bytesIn;}
    public long getbytesOut() {return bytesOut;}

    @Override
    public long getSensorId() {
        return SENSOR_ID;
    }

    @Override
    public SensorData toProtoSensor() {
        SensorData.Builder sdb = SensorData.newBuilder();
        sdb.setRecordTime(getTimestamp());
        sdb.addValueString(appName);
        sdb.addValueInt64(bytesIn);
        sdb.addValueInt64(bytesOut);
        return sdb.build();
    }


}
