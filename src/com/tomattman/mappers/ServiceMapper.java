package com.tomattman.mappers;

import com.tomattman.model.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServiceMapper {
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final String SEPARATOR = " ";

    public static Service stringToService(String line){
        Service service = new Service();
        String[] parts = line.split(SEPARATOR);
        service.setCompanyName(parts[0]);
        service.setDepartureTime(LocalTime.parse(parts[1], timeFormatter));
        service.setArrivalTime(LocalTime.parse(parts[2], timeFormatter));
        return service;
    }

    public static String serviceToLine(Service service){
        return service.getCompanyName() + SEPARATOR + service.getDepartureTime().format(timeFormatter) + SEPARATOR + service.getArrivalTime().format(timeFormatter);
    }
}
