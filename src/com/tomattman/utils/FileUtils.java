package com.tomattman.utils;

import com.tomattman.comparators.DepartureTimeComparator;
import com.tomattman.mappers.ServiceMapper;
import com.tomattman.model.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tomattman.utils.Constants.FIRST_COMPANY;
import static com.tomattman.utils.Constants.SECOND_COMPANY;

public class FileUtils {
    private final static String OUTPUT_FILE_NAME = "output.txt";

    public static List<Service> readServicesFromFile(String fileName) throws IOException {
        List<Service> services = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        while (bufferedReader.ready()) {
            services.add(ServiceMapper.stringToService(bufferedReader.readLine()));
        }
        bufferedReader.close();
        return services;
    }

    public static void writeServicesToFile(List<Service> services) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        services.sort(new DepartureTimeComparator());

        writeCompanyServices(bufferedWriter, services, FIRST_COMPANY);
        bufferedWriter.write("\n\n");
        writeCompanyServices(bufferedWriter, services, SECOND_COMPANY);

        bufferedWriter.close();
    }

    public static void writeCompanyServices(BufferedWriter bufferedWriter, List<Service> services, String companyName) throws IOException {
        bufferedWriter.write(services.stream().filter(s -> s.getCompanyName().equals(companyName)).map(ServiceMapper::serviceToLine).collect(Collectors.joining("\n")));
    }
}
