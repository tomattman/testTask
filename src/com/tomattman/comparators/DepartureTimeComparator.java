package com.tomattman.comparators;

import com.tomattman.model.Service;

import java.util.Comparator;

import static java.time.temporal.ChronoUnit.MINUTES;

public class DepartureTimeComparator implements Comparator<Service> {
    @Override
    public int compare(Service o1, Service o2) {
        return (int) MINUTES.between(o2.getDepartureTime(), o1.getDepartureTime());
    }
}
