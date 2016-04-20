package ch.ethz.soms.nervous.android.sensorQueries;

import java.io.File;
import java.util.ArrayList;

import ch.ethz.soms.nervous.android.Queries.Query;
import ch.ethz.soms.nervous.android.sensors.SensorDescNotification;
import ch.ethz.soms.nervous.nervousproto.SensorUploadProtos;

/**
 * Created by grg on 19/04/16.
 */
public class SensorQueriesNotification extends Query<SensorDescNotification> {

    @Override
    public long getSensorId() {
        return SensorDescNotification.SENSOR_ID;
    }

    public SensorQueriesNotification(long timestamp_from, long timestamp_to, File file) {
        super(timestamp_from, timestamp_to, file);
    }

    public ArrayList<SensorDescNotification> getSensorDescriptorList() {
        ArrayList<SensorDescNotification> descList = new ArrayList<SensorDescNotification>();
        try {
            for (SensorUploadProtos.SensorUpload.SensorData sensorData : list) {
                descList.add(new SensorDescNotification(sensorData));
            }
        } catch(Exception e1){
            System.out.println(e1);
        }
        return descList;
    }
}
