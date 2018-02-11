package com.zyjd.tijia.entity;

import java.util.List;

public class FaultRecord {
    private int id;
    private String record_from;
    private int elevator;
    private Elevator elevator_detail;
    private String fault_code;
    private String fault_desc;
    private String occur_time;
    private boolean ppl_traped;
    private String handle_status;
    private String handle_time;
    private String handle_result;
    private String end_time;
    private String desc;
    private String station;
    private String direction;
    private String have_ppl;
    private String door_status;
    private String sys_power;
    private String safety_circuit;
    private String door_lock_circuit;
    private String brake_status;
    private List<Integer> handle_ppl;
    private List<User> handle_ppl_detail;
    private List<Integer> files;
    private List<File> files_detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecord_from() {
        return record_from;
    }

    public void setRecord_from(String record_from) {
        this.record_from = record_from;
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

    public String getFault_code() {
        return fault_code;
    }

    public void setFault_code(String fault_code) {
        this.fault_code = fault_code;
    }

    public String getFault_desc() {
        return fault_desc;
    }

    public void setFault_desc(String fault_desc) {
        this.fault_desc = fault_desc;
    }

    public String getOccur_time() {
        return occur_time;
    }

    public void setOccur_time(String occur_time) {
        this.occur_time = occur_time;
    }

    public boolean isPpl_traped() {
        return ppl_traped;
    }

    public void setPpl_traped(boolean ppl_traped) {
        this.ppl_traped = ppl_traped;
    }

    public String getHandle_status() {
        return handle_status;
    }

    public void setHandle_status(String handle_status) {
        this.handle_status = handle_status;
    }

    public String getHandle_time() {
        return handle_time;
    }

    public void setHandle_time(String handle_time) {
        this.handle_time = handle_time;
    }

    public String getHandle_result() {
        return handle_result;
    }

    public void setHandle_result(String handle_result) {
        this.handle_result = handle_result;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getHave_ppl() {
        return have_ppl;
    }

    public void setHave_ppl(String have_ppl) {
        this.have_ppl = have_ppl;
    }

    public String getDoor_status() {
        return door_status;
    }

    public void setDoor_status(String door_status) {
        this.door_status = door_status;
    }

    public String getSys_power() {
        return sys_power;
    }

    public void setSys_power(String sys_power) {
        this.sys_power = sys_power;
    }

    public String getSafety_circuit() {
        return safety_circuit;
    }

    public void setSafety_circuit(String safety_circuit) {
        this.safety_circuit = safety_circuit;
    }

    public String getDoor_lock_circuit() {
        return door_lock_circuit;
    }

    public void setDoor_lock_circuit(String door_lock_circuit) {
        this.door_lock_circuit = door_lock_circuit;
    }

    public String getBrake_status() {
        return brake_status;
    }

    public void setBrake_status(String brake_status) {
        this.brake_status = brake_status;
    }

    public List<Integer> getHandle_ppl() {
        return handle_ppl;
    }

    public void setHandle_ppl(List<Integer> handle_ppl) {
        this.handle_ppl = handle_ppl;
    }

    public List<User> getHandle_ppl_detail() {
        return handle_ppl_detail;
    }

    public void setHandle_ppl_detail(List<User> handle_ppl_detail) {
        this.handle_ppl_detail = handle_ppl_detail;
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
