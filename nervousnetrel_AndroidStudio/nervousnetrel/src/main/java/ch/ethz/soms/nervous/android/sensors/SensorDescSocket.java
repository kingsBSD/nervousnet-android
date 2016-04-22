package ch.ethz.soms.nervous.android.sensors;

import ch.ethz.soms.nervous.nervousproto.SensorUploadProtos;

/**
 * Created by grg on 22/04/16.
 */
public class SensorDescSocket extends SensorDesc {

    private final String appName;
    private final String protocol;
    private final int port;

    public static final long SENSOR_ID = 0x0000000000000015L;

    public SensorDescSocket(final long timestamp, final String appName, final String protocol, final int port) {
        super(timestamp);
        this.appName = appName;
        this.protocol= protocol;
        this.port = port;
    }

    public SensorDescSocket(SensorUploadProtos.SensorUpload.SensorData sensorData) {
        super(sensorData);
        appName = sensorData.getValueString(0);
        protocol = sensorData.getValueString(1);
        port = sensorData.getValueInt32(0);
    }

    public String getAppName() {return appName;}
    public String getProtocol() {return protocol;}
    public int getPort() {return port;}

    @Override
    public long getSensorId() {return SENSOR_ID;}

    @Override
    public SensorUploadProtos.SensorUpload.SensorData toProtoSensor() {
        SensorUploadProtos.SensorUpload.SensorData.Builder sdb = SensorUploadProtos.SensorUpload.SensorData.newBuilder();
        sdb.setRecordTime(getTimestamp());
        sdb.addValueString(appName);
        sdb.addValueString(protocol);
        sdb.addValueInt32(port);
        return sdb.build();
    }

}
