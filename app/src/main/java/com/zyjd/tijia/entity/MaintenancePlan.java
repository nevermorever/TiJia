package com.zyjd.tijia.entity;


import java.util.List;

public class MaintenancePlan {
    private int id;
    private String name;
    private String type;
    private String maintenance_items;
    private int elevator;
    private Elevator elevator_detail;
    private String start;
    private String end;
    private String actual_end;
    private boolean is_active;
    private String execution_status;
    private String desc;
    private List<Integer> executors;
    private List<User> executors_detail;
    private List<Integer> files;
    private List<File> files_detail;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaintenance_items() {
        return maintenance_items;
    }

    public void setMaintenance_items(String maintenance_items) {
        this.maintenance_items = maintenance_items;
    }

    public int getElevator() {
        return elevator;
    }

    public void setElevator(int elevator) {
        this.elevator = elevator;
    }

    public Elevator getElevator_detail() {
        return elevator_detail;
    }

    public void setElevator_detail(Elevator elevator_detail) {
        this.elevator_detail = elevator_detail;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getActual_end() {
        return actual_end;
    }

    public void setActual_end(String actual_end) {
        this.actual_end = actual_end;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getExecution_status() {
        return execution_status;
    }

    public void setExecution_status(String execution_status) {
        this.execution_status = execution_status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Integer> getExecutors() {
        return executors;
    }

    public void setExecutors(List<Integer> executors) {
        this.executors = executors;
    }

    public List<User> getExecutors_detail() {
        return executors_detail;
    }

    public void setExecutors_detail(List<User> executors_detail) {
        this.executors_detail = executors_detail;
    }

    public List<Integer> getFiles() {
        return files;
    }

    public void setFiles(List<Integer> files) {
        this.files = files;
    }

    public List<File> getFiles_detail() {
        return files_detail;
    }

    public void setFiles_detail(List<File> files_detail) {
        this.files_detail = files_detail;
    }
}
