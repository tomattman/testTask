package com.tomattman;

import com.tomattman.comparators.EfficientComparator;
import com.tomattman.model.Service;
import com.tomattman.utils.ServiceCompareUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

public class EfficientServicesFinder {
    public static List<Service> findEfficientServices(List<Service> services){
        services = services.stream().filter(EfficientServicesFinder::isHandledService).collect(Collectors.toList());

        return filterEfficientService(services);
    }

    private static boolean isHandledService(Service service){
        long between = MINUTES.between(service.getDepartureTime(), service.getArrivalTime());
        return between <= 60 &&
                (between > 0 || between < -60 * 23)
                ;
    }

    private static List<Service> filterEfficientService(List<Service> services){
        List<Service> efficientServices = new ArrayList<>();
        services.sort(new EfficientComparator());
        efficientServices.add(services.get(0));
        for(int i = 1; i < services.size(); i ++){
            if(!areInOneComparableGroup(services.get(i - 1), services.get(i))){
                efficientServices.add(services.get(i));
            }
        }
        return efficientServices;
    }

    private static boolean areInOneComparableGroup(Service s1, Service s2){
        return !(ServiceCompareUtil.isFirstDepartEarlier(s1, s2) && ServiceCompareUtil.isFirstArriveEarlier(s1, s2));

    }
}
