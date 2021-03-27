package com.tomattman.utils;

import com.tomattman.model.Service;

import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class ServiceCompareUtil {
    public static boolean isFirstDepartEarlier(Service s1, Service s2){
        return isFirstTimeEarlier(s1.getDepartureTime(), s2.getDepartureTime());
    }

    public static boolean isFirstArriveEarlier(Service s1, Service s2){
        return isFirstTimeEarlier(s1.getArrivalTime(), s2.getArrivalTime());
    }

    private static boolean isFirstTimeEarlier(LocalTime t1, LocalTime t2){
        return MINUTES.between(t1, t2) > 0 || MINUTES.between(t1, t2) < -1380 ;
    }
}
