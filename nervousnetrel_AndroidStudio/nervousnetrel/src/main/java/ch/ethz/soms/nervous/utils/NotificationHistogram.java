package ch.ethz.soms.nervous.utils;

import java.util.ArrayList;
import java.util.Calendar;

import ch.ethz.soms.nervous.android.sensorQueries.SensorQueriesNotification;
import ch.ethz.soms.nervous.android.sensors.SensorDescNotification;

/**
 * Created by grg on 22/04/16.
 */
public class NotificationHistogram {
    // The date-picker for the charts allows you to specify times to the nearest hour.
    // Might as well plot the receipt of notifications in 1-hour bins.
    public NotificationHistogram() {

    }

    // Churns out suitable JSON for shoving into the chart webview.
    public static String build(SensorQueriesNotification q, String app) {

        ArrayList<SensorDescNotification> filteredResults;

        if (app != null && !app.isEmpty()) {
            filteredResults = filter(q,app);
        } else {
            filteredResults = filter(q);
        }

        Calendar thisCal = Calendar.getInstance();
        Calendar nextCal = Calendar.getInstance();
        thisCal.setTimeInMillis(0);
        int thisHour = thisCal.get(Calendar.HOUR_OF_DAY);
        int nextHour = -1;
        long total = 0;
        boolean gotData = false;

        String data_array = "[";

        // Might be polite to run a progress dialogue over this loop.
        for (SensorDescNotification desc: filteredResults) {
            nextCal.setTimeInMillis(desc.getTimestamp());
            nextHour = nextCal.get(Calendar.HOUR_OF_DAY);
            if (nextHour != thisHour) {
                if (gotData) {
                    data_array += renderDataValue(thisCal,total)+",";
                } else {
                    //  No longer circa 1970, so we can start counting...
                    total = 1;
                    gotData = true;
                }
                thisHour = nextHour;
                thisCal = nextCal;
            } else {
                total += 1;
            }
        }

        data_array += renderDataValue(thisCal,total) + "]";

        return data_array;
    }

    private static String renderDataValue(Calendar cal, long val ) {
        return "[Date.UTC("+cal.get(Calendar.YEAR)+","
                +cal.get(Calendar.MONTH)+","
                +cal.get(Calendar.DAY_OF_MONTH)+","
                +cal.get(Calendar.HOUR_OF_DAY)+","
                +cal.get(Calendar.MINUTE)+","
                +cal.get(Calendar.SECOND)+"),"+Long.toString(val)+"]";
    }

    private static ArrayList<SensorDescNotification> filter(SensorQueriesNotification q) {
        ArrayList<SensorDescNotification> results = new ArrayList<SensorDescNotification>();
        return q.getSensorDescriptorList();
    }

    // The dream is to filter notification events by app from the chart activity.
    private static  ArrayList<SensorDescNotification> filter (SensorQueriesNotification q, String app) {
        ArrayList<SensorDescNotification> results = new ArrayList<SensorDescNotification>();
        for (SensorDescNotification desc: q.getSensorDescriptorList()) {
            if (desc.getAppName().equalsIgnoreCase(app)) {
                results.add(desc);
            }
        }
        return results;
    }
}
