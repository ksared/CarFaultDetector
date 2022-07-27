package com.example.carfaultdetector.model;

import java.util.List;

public class Fault {
    private String name;

    private List<Integer> issueList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<Integer> issueList) {
        this.issueList = issueList;
    }
}
