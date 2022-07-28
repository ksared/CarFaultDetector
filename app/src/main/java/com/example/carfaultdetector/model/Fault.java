package com.example.carfaultdetector.model;

import java.util.List;

public class Fault {
    private String name;

    private List<Double> issueList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<Double> issueList) {
        this.issueList = issueList;
    }
}
