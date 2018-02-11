package com.zyjd.tijia.entity;


import java.util.List;

public class RepairRecord {
    private int id;
    private String type;
    private Integer elevator;
    private Elevator elevator_detail;
    private String start_time;
    private String end_time;
    private String check_content;
    private String repair_content;
    private String fittings_replace;
    private String desc;
    private Integer call_record;
    private CallRecord call_record_detail;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getElevator() {
        return elevator;
    }

    public void setElevator(Integer elevator) {
        this.elevator = elevator;
    }

    public void setCall_record(Integer call_record) {
        this.call_record = call_record;
    }

    public Elevator getElevator_detail() {
        return elevator_detail;
    }

    public void setElevator_detail(Elevator elevator_detail) {
        this.elevator_detail = elevator_detail;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCheck_content() {
        return check_content;
    }

    public void setCheck_content(String check_content) {
        this.check_content = check_content;
    }

    public String getRepair_content() {
        return repair_content;
    }

    public void setRepair_content(String repair_content) {
        this.repair_content = repair_content;
    }

    public String getFittings_replace() {
        return fittings_replace;
    }

    public void setFittings_replace(String fittings_replace) {
        this.fittings_replace = fittings_replace;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCall_record() {
        return call_record;
    }

    public void setCall_record(int call_record) {
        this.call_record = call_record;
    }

    public CallRecord getCall_record_detail() {
        return call_record_detail;
    }

    public void setCall_record_detail(CallRecord call_record_detail) {
        this.call_record_detail = call_record_detail;
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
