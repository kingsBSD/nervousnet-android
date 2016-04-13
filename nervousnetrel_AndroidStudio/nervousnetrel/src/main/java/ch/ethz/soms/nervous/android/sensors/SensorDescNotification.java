package ch.ethz.soms.nervous.android.sensors;

/**
 * Created by grg on 11/04/16.
 */

import ch.ethz.soms.nervous.nervousproto.SensorUploadProtos.SensorUpload.SensorData;

public class SensorDescNotification extends SensorDesc {

    public static final long SENSOR_ID = 0x0000000000000013L;

    private final String appName;

    public SensorDescNotification(final long timestamp, final String appName) {
        super(timestamp);
        this.appName = appName;
    }

    public SensorDescNotification(SensorData sensorData) {
        super(sensorData);
        appName = sensorData.getValueString(0);
    }

    public String getAppName() {return appName;}

    @Override
    public long getSensorId() {
        return SENSOR_ID;
    }

    @Override
    public SensorData toProtoSensor() {
        SensorData.Builder sdb = SensorData.newBuilder();
        sdb.setRecordTime(getTimestamp());
        sdb.addValueString(appName);
        return sdb.build();
    }


}
