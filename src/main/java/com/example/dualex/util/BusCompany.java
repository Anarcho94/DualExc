package com.example.dualex.util;

public enum BusCompany {
    POSH("Posh"),
    GROTTY("Grotty");

    private final String companyName;

    BusCompany(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}