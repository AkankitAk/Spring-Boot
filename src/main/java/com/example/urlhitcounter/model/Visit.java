package com.example.urlhitcounter.model;

public class Visit {
    private String username;

    private int count;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Visit(String username,int i) {
        this.username=username;
        this.count=i;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "count=" + count +
                '}';
    }
}
