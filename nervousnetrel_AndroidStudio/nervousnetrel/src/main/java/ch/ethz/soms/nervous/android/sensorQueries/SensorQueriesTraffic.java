package ch.ethz.soms.nervous.android.sensorQueries;

import java.io.File;
import java.util.ArrayList;

import ch.ethz.soms.nervous.android.Queries.Query;
import ch.ethz.soms.nervous.android.sensors.SensorDescTraffic;
import ch.ethz.soms.nervous.nervousproto.SensorUploadProtos;

/**
 * Created by grg on 22/04/16.
 */
public class SensorQueriesTraffic extends Query<SensorDescTraffic> {

    @Override
    public long getSensorId() {
        return SensorDescTraffic.SENSOR_ID;
    }

    public SensorQueriesTraffic(long timestamp_from, long timestamp_to, File file) {
        super(timestamp_from, timestamp_to, file);
    }

    public ArrayList<SensorDescTraffic> getSensorDescriptorList() {
        ArrayList<SensorDescTraffic> descList = new ArrayList<SensorDescTraffic>();
        try {
            for (SensorUploadProtos.SensorUpload.SensorData sensorData : list) {
                descList.add(new SensorDescTraffic(sensorData));
            }
        } catch(Exception e1){
            System.out.println(e1);
        }
        return descList;
    }

}

