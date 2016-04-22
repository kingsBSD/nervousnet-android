package ch.ethz.soms.nervous.android.sensorQueries;

import java.io.File;
import java.util.ArrayList;

import ch.ethz.soms.nervous.android.Queries.Query;
import ch.ethz.soms.nervous.android.sensors.SensorDescSocket;
import ch.ethz.soms.nervous.nervousproto.SensorUploadProtos;

/**
 * Created by grg on 22/04/16.
 */

public class SensorQueriesSocket extends Query<SensorDescSocket> {

    @Override
    public long getSensorId() {
        return SensorDescSocket.SENSOR_ID;
    }

    public SensorQueriesSocket(long timestamp_from, long timestamp_to, File file) {
        super(timestamp_from, timestamp_to, file);
    }

    public ArrayList<SensorDescSocket> getSensorDescriptorList() {
        ArrayList<SensorDescSocket> descList = new ArrayList<SensorDescSocket>();
        try {
            for (SensorUploadProtos.SensorUpload.SensorData sensorData : list) {
                descList.add(new SensorDescSocket(sensorData));
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return descList;
    }

}
