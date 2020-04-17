package com.example.dualex;

import com.example.dualex.service.BusRecordService;
import com.example.dualex.service.InputFileService;

import java.io.IOException;

public class DualExMain {
    public static void main(String[] args) {
        BusRecordService recordService = new BusRecordService();
        try {
            recordService.makeBusSchedule("./testInputFile.txt");
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }
}
