package com.example.myapp.model;

public class MitbewohniModel {
    private String mitbewohniName;
    private int numberOfRings;

    public MitbewohniModel(String mitbewohniName, int numberOfRings){
        this.mitbewohniName = mitbewohniName;
        this.numberOfRings = numberOfRings;
    }

    public String getMitbewohniName() {
        return this.mitbewohniName;
    }

    public int getNumberOfRings() {
        return numberOfRings;
    }
}
