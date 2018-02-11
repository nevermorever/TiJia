package com.zyjd.tijia.entity;


public class Terminal {

    private int id;
    private String name;
    private String ip;
    private String hx_id;
    private String install_location;
    private int elevator;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHx_id() {
        return hx_id;
    }

    public void setHx_id(String hx_id) {
        this.hx_id = hx_id;
    }

    public String getInstall_location() {
        return install_location;
    }

    public void setInstall_location(String install_location) {
        this.install_location = install_location;
    }

    public int getElevator() {
        return elevator;
    }

    public void setElevator(int elevator) {
        this.elevator = elevator;
    }
}
