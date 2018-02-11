package com.zyjd.tijia.entity;


public class Area {
    private int id;
    private String name;
    private int org;
    private Org org_detail;
    private int device_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrg() {
        return org;
    }

    public void setOrg(int org) {
        this.org = org;
    }

    public Org getOrg_detail() {
        return org_detail;
    }

    public void setOrg_detail(Org org_detail) {
        this.org_detail = org_detail;
    }

    public int getDevice_count() {
        return device_count;
    }

    public void setDevice_count(int device_count) {
        this.device_count = device_count;
    }
}
