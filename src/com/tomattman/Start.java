package com.tomattman;

import com.tomattman.model.Service;
import com.tomattman.utils.FileUtils;

import java.io.IOException;
import java.util.List;

public class Start {

    public static void main(String[] args) throws IOException {
        String inputFileName = args[0];

        List<Service> services = FileUtils.readServicesFromFile(inputFileName);

        List<Service> efficientServices = EfficientServicesFinder.findEfficientServices(services);

        FileUtils.writeServicesToFile(efficientServices);
    }


}
