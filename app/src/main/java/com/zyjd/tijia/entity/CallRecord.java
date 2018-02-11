package com.zyjd.tijia.entity;

public class CallRecord {


    private int id;
    private String call_number;
    private String call_time;
    private String call_org;
    private String call_reason;
    private Integer on_duty_ppl;
    private User on_duty_ppl_detail;
    private Integer elevator;
    private Elevator elevator_detail;
    private String handle_status;
    private String handle_result;
    private String call_content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCall_number() {
        return call_number;
    }

    public void setCall_number(String call_number) {
        this.call_number = call_number;
    }

    public String getCall_time() {
        return call_time;
    }

    public void setCall_time(String call_time) {
        this.call_time = call_time;
    }

    public String getCall_org() {
        return call_org;
    }

    public void setCall_org(String call_org) {
        this.call_org = call_org;
    }

    public String getCall_reason() {
        return call_reason;
    }

    public void setCall_reason(String call_reason) {
        this.call_reason = call_reason;
    }

    public Integer getOn_duty_ppl() {
        return on_duty_ppl;
    }

    public void setOn_duty_ppl(Integer on_duty_ppl) {
        this.on_duty_ppl = on_duty_ppl;
    }

    public User getOn_duty_ppl_detail() {
        return on_duty_ppl_detail;
    }

    public void setOn_duty_ppl_detail(User on_duty_ppl_detail) {
        this.on_duty_ppl_detail = on_duty_ppl_detail;
    }

    public Integer getElevator() {
        return elevator;
    }

    public void setElevator(Integer elevator) {
        this.elevator = elevator;
    }

    public Elevator getElevator_detail() {
        return elevator_detail;
    }

    public void setElevator_detail(Elevator elevator_detail) {
        this.elevator_detail = elevator_detail;
    }

    public String getHandle_status() {
        return handle_status;
    }

    public void setHandle_status(String handle_status) {
        this.handle_status = handle_status;
    }

    public String getHandle_result() {
        return handle_result;
    }

    public void setHandle_result(String handle_result) {
        this.handle_result = handle_result;
    }

    public String getCall_content() {
        return call_content;
    }

    public void setCall_content(String call_content) {
        this.call_content = call_content;
    }
}
