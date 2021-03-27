package com.tomattman.comparators;

import com.tomattman.model.Service;

import java.util.Comparator;

import static com.tomattman.utils.Constants.FIRST_COMPANY;
import static java.time.temporal.ChronoUnit.MINUTES;

public class EfficientComparator implements Comparator<Service> {
    @Override
    public int compare(Service o1, Service o2) {
        if (MINUTES.between(o1.getArrivalTime(), o2.getArrivalTime()) != 0) {
            return (int) MINUTES.between(o2.getArrivalTime(), o1.getArrivalTime());
        }
        if(MINUTES.between(o1.getDepartureTime(), o2.getDepartureTime()) != 0 ){
            long timeRange1 = MINUTES.between(o1.getDepartureTime(), o1.getArrivalTime());
            timeRange1 = timeRange1 > 0 ? timeRange1 : timeRange1 + 60 * 24;
            long timeRange2 = MINUTES.between(o2.getDepartureTime(), o2.getArrivalTime());
            timeRange2 = timeRange2 > 0 ? timeRange2 : timeRange2 + 60 * 24;
            return (int)(timeRange1 - timeRange2);
        }
        if(o1.getCompanyName().equals(FIRST_COMPANY)){
            return -1;
        }
        return 0;
    }
}
