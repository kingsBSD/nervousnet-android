package ch.ethz.soms.nervous.android.sensorQueries;

import java.io.File;
import java.util.ArrayList;

import ch.ethz.soms.nervous.android.Queries.Query;
import ch.ethz.soms.nervous.android.sensors.SensorDescBLEBeacon;
import ch.ethz.soms.nervous.nervousproto.SensorUploadProtos;

/**
 * Created by grg on 19/04/16.
 */
public class SensorQueriesBLEBeacon extends Query<SensorDescBLEBeacon> {

    @Override
    public long getSensorId() {
        return SensorDescBLEBeacon.SENSOR_ID;
    }

    public SensorQueriesBLEBeacon(long timestamp_from, long timestamp_to, File file) {
        super(timestamp_from, timestamp_to, file);
    }
    public ArrayList<SensorDescBLEBeacon> getSensorDescriptorList() {
        ArrayList<SensorDescBLEBeacon> descList = new ArrayList<SensorDescBLEBeacon>();
        try {
            for (SensorUploadProtos.SensorUpload.SensorData sensorData : list) {
                descList.add(new SensorDescBLEBeacon(sensorData));
            }
        } catch(Exception e1){
            System.out.println(e1);
        }
        return descList;
    }

}
