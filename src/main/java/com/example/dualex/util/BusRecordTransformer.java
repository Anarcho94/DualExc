package com.example.dualex.util;

import com.example.dualex.entity.BusRecord;

import java.time.LocalTime;

public class BusRecordTransformer {

    public BusRecord toBusRecord(String recordLine) {
        String[] stringData = (recordLine.split(" "));
        return new BusRecord(BusCompany.valueOf(stringData[0].toUpperCase()), LocalTime.parse(stringData[1]), LocalTime.parse(stringData[2]));
    }
}
