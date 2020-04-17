package com.example.dualex.service;

import com.example.dualex.entity.BusRecord;
import com.example.dualex.util.BusRecordTransformer;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.example.dualex.util.BusCompany.GROTTY;
import static com.example.dualex.util.BusCompany.POSH;

public class BusRecordService {

    public void makeBusSchedule(String inputPath, String outputPath) throws IOException {
        List<BusRecord> commonSchedule = makeSchedule(inputPath);
        chooseEffective(commonSchedule);
        writeInFile(commonSchedule, outputPath);
    }


    private Predicate<BusRecord> moreThanHourPredicate() {
        return busRecord -> busRecord.getArrivalToDestination().toSecondOfDay()
                - busRecord.getArrivalToBusStop().toSecondOfDay() < 3600;
    }

    private List<BusRecord> makeSchedule(String path) throws IOException {
        InputFileService fileService = new InputFileService();
        BusRecordTransformer transformer = new BusRecordTransformer();
        return fileService.fileToArrayList(path).stream()
                .map(transformer::toBusRecord)
                .filter(moreThanHourPredicate())
                .sorted(Comparator.comparingInt(o -> o.getArrivalToDestination().toSecondOfDay()))
                .collect(Collectors.toList());
    }

    private void chooseEffective(List<BusRecord> commonList) {
        List<BusRecord> list = new ArrayList<>();
        for (BusRecord busRecord : commonList) {
            for (BusRecord record : commonList) {
                if (!busRecord.equals(record) && removeCondition(busRecord, record)) {
                    list.add(busRecord);
                }
            }
        }
        commonList.removeAll(list);
    }

    private boolean removeCondition(BusRecord record1, BusRecord record2) {
        return (record1.getArrivalToBusStop().equals(record2.getArrivalToBusStop())
                && record2.getArrivalToDestination().equals(record1.getArrivalToDestination())
                && record1.getName().equals(GROTTY))
                || (record1.getArrivalToBusStop().equals(record2.getArrivalToBusStop())
                && record1.getArrivalToDestination().isBefore(record2.getArrivalToDestination()))
                || (record1.getArrivalToDestination().equals(record2.getArrivalToDestination())
                && record1.getArrivalToBusStop().isBefore(record2.getArrivalToBusStop()))
                || (record2.getArrivalToBusStop().isBefore(record1.getArrivalToBusStop())
                && record1.getArrivalToDestination().isBefore(record2.getArrivalToDestination()));
    }

    private void writeInFile(List<BusRecord> commonSchedule, String outputPath) throws IOException {
        List<BusRecord> poshList = commonSchedule.stream().filter(busRecord -> busRecord.getName().equals(POSH))
                .collect(Collectors.toList());
        List<BusRecord> grottyList = commonSchedule.stream().filter(busRecord -> busRecord.getName().equals(GROTTY))
                .collect(Collectors.toList());
        FileWriter fw = new FileWriter(outputPath);
        PrintWriter printWriter = new PrintWriter(fw);
        for (BusRecord busRecord : poshList) {
            printWriter.println(busRecord.toString());
        }
        if (!grottyList.isEmpty()) {
            printWriter.println("");
            for (BusRecord busRecord : grottyList) {
                printWriter.println(busRecord.toString());
            }
        }
        printWriter.close();
    }

}
