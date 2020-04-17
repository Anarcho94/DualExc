package com.example.dualex.entity;

import com.example.dualex.util.BusCompany;

import java.time.LocalTime;

public class BusRecord {
    private BusCompany name;

    private LocalTime arrivalToBusStop;

    private LocalTime arrivalToDestination;

    public BusRecord(BusCompany name, LocalTime arrivalToBusStop, LocalTime arrivalToDestination) {
        this.name = name;
        this.arrivalToBusStop = arrivalToBusStop;
        this.arrivalToDestination = arrivalToDestination;
    }

    public BusCompany getName() {
        return name;
    }

    public void setName(BusCompany name) {
        this.name = name;
    }

    public LocalTime getArrivalToBusStop() {
        return arrivalToBusStop;
    }

    public void setArrivalToBusStop(LocalTime arrivalToBusStop) {
        this.arrivalToBusStop = arrivalToBusStop;
    }

    public LocalTime getArrivalToDestination() {
        return arrivalToDestination;
    }

    public void setArrivalToDestination(LocalTime arrivalToDestination) {
        this.arrivalToDestination = arrivalToDestination;
    }

    @Override
    public String toString() {
        return this.getName().getCompanyName() + " " + this.getArrivalToBusStop() + " " + this.getArrivalToDestination();
    }
}
