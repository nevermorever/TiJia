package com.zyjd.tijia.entity;

import java.util.List;

public class Elevator {
    private int id;
    private int belong_org;
    private int area;
    private int maintenance_org;
    private Area area_detail;
    private Org belong_org_detail;
    private Org maintenance_org_detail;
    private String ip;
    private Boolean is_online;
    private String created;
    private String modified;
    private String type;
    private String name;
    private String sn;
    private String manufacturer;
    private String product_model;
    private String start_using_date;
    private String control_mode;
    private String main_motor_power;
    private String lifting_height;
    private String rated_speed;
    private String rated_load;
    private String lng;
    private String lat;
    private String next_inspection_date;
    private String base_station;
    private String step_width;
    private String transport_capacity;
    private String use_segment_length;
    private String tilt_angle;
    private String handrail_length;
    private String handrail_size;
    private String door_open_mode;
    private int floor;
    private int station;
    private String pit_depth;
    private String traction_machine_model;
    private String speed_limiter_model;
    private String traction_wheel_diameter;
    private String landing_door_size;
    private String cable_diameter;
    private String buffer_model;
    private String door_open_width;
    private String top_floor_height;
    private String reducer_speed_ratio;
    private String main_motor_speed;
    private String speed_limiter_action_speed;
    private String door_motor_model;
    private String cage_size;
    private String safety_gea_model;
    private String well_size;
    private String usage;
    private String door_control_system_type;
    private boolean have_power_failure_reflatfloor;
    private boolean have_energy_feedback_system;

    private List<String> station_mapping_list;
    private List<Integer> belong_org_charge_ppl;
    private List<Integer> maintenance_ppl;
    private List<User> maintenance_ppl_detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBelong_org() {
        return belong_org;
    }

    public void setBelong_org(int belong_org) {
        this.belong_org = belong_org;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getMaintenance_org() {
        return maintenance_org;
    }

    public void setMaintenance_org(int maintenance_org) {
        this.maintenance_org = maintenance_org;
    }

    public Area getArea_detail() {
        return area_detail;
    }

    public void setArea_detail(Area area_detail) {
        this.area_detail = area_detail;
    }

    public Org getBelong_org_detail() {
        return belong_org_detail;
    }

    public void setBelong_org_detail(Org belong_org_detail) {
        this.belong_org_detail = belong_org_detail;
    }

    public Org getMaintenance_org_detail() {
        return maintenance_org_detail;
    }

    public void setMaintenance_org_detail(Org maintenance_org_detail) {
        this.maintenance_org_detail = maintenance_org_detail;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getIs_online() {
        return is_online;
    }

    public void setIs_online(Boolean is_online) {
        this.is_online = is_online;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProduct_model() {
        return product_model;
    }

    public void setProduct_model(String product_model) {
        this.product_model = product_model;
    }

    public String getStart_using_date() {
        return start_using_date;
    }

    public void setStart_using_date(String start_using_date) {
        this.start_using_date = start_using_date;
    }

    public String getControl_mode() {
        return control_mode;
    }

    public void setControl_mode(String control_mode) {
        this.control_mode = control_mode;
    }

    public String getMain_motor_power() {
        return main_motor_power;
    }

    public void setMain_motor_power(String main_motor_power) {
        this.main_motor_power = main_motor_power;
    }

    public String getLifting_height() {
        return lifting_height;
    }

    public void setLifting_height(String lifting_height) {
        this.lifting_height = lifting_height;
    }

    public String getRated_speed() {
        return rated_speed;
    }

    public void setRated_speed(String rated_speed) {
        this.rated_speed = rated_speed;
    }

    public String getRated_load() {
        return rated_load;
    }

    public void setRated_load(String rated_load) {
        this.rated_load = rated_load;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getNext_inspection_date() {
        return next_inspection_date;
    }

    public void setNext_inspection_date(String next_inspection_date) {
        this.next_inspection_date = next_inspection_date;
    }

    public String getBase_station() {
        return base_station;
    }

    public void setBase_station(String base_station) {
        this.base_station = base_station;
    }

    public String getStep_width() {
        return step_width;
    }

    public void setStep_width(String step_width) {
        this.step_width = step_width;
    }

    public String getTransport_capacity() {
        return transport_capacity;
    }

    public void setTransport_capacity(String transport_capacity) {
        this.transport_capacity = transport_capacity;
    }

    public String getUse_segment_length() {
        return use_segment_length;
    }

    public void setUse_segment_length(String use_segment_length) {
        this.use_segment_length = use_segment_length;
    }

    public String getTilt_angle() {
        return tilt_angle;
    }

    public void setTilt_angle(String tilt_angle) {
        this.tilt_angle = tilt_angle;
    }

    public String getHandrail_length() {
        return handrail_length;
    }

    public void setHandrail_length(String handrail_length) {
        this.handrail_length = handrail_length;
    }

    public String getHandrail_size() {
        return handrail_size;
    }

    public void setHandrail_size(String handrail_size) {
        this.handrail_size = handrail_size;
    }

    public String getDoor_open_mode() {
        return door_open_mode;
    }

    public void setDoor_open_mode(String door_open_mode) {
        this.door_open_mode = door_open_mode;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public String getPit_depth() {
        return pit_depth;
    }

    public void setPit_depth(String pit_depth) {
        this.pit_depth = pit_depth;
    }

    public String getTraction_machine_model() {
        return traction_machine_model;
    }

    public void setTraction_machine_model(String traction_machine_model) {
        this.traction_machine_model = traction_machine_model;
    }

    public String getSpeed_limiter_model() {
        return speed_limiter_model;
    }

    public void setSpeed_limiter_model(String speed_limiter_model) {
        this.speed_limiter_model = speed_limiter_model;
    }

    public String getTraction_wheel_diameter() {
        return traction_wheel_diameter;
    }

    public void setTraction_wheel_diameter(String traction_wheel_diameter) {
        this.traction_wheel_diameter = traction_wheel_diameter;
    }

    public String getLanding_door_size() {
        return landing_door_size;
    }

    public void setLanding_door_size(String landing_door_size) {
        this.landing_door_size = landing_door_size;
    }

    public String getCable_diameter() {
        return cable_diameter;
    }

    public void setCable_diameter(String cable_diameter) {
        this.cable_diameter = cable_diameter;
    }

    public String getBuffer_model() {
        return buffer_model;
    }

    public void setBuffer_model(String buffer_model) {
        this.buffer_model = buffer_model;
    }

    public String getDoor_open_width() {
        return door_open_width;
    }

    public void setDoor_open_width(String door_open_width) {
        this.door_open_width = door_open_width;
    }

    public String getTop_floor_height() {
        return top_floor_height;
    }

    public void setTop_floor_height(String top_floor_height) {
        this.top_floor_height = top_floor_height;
    }

    public String getReducer_speed_ratio() {
        return reducer_speed_ratio;
    }

    public void setReducer_speed_ratio(String reducer_speed_ratio) {
        this.reducer_speed_ratio = reducer_speed_ratio;
    }

    public String getMain_motor_speed() {
        return main_motor_speed;
    }

    public void setMain_motor_speed(String main_motor_speed) {
        this.main_motor_speed = main_motor_speed;
    }

    public String getSpeed_limiter_action_speed() {
        return speed_limiter_action_speed;
    }

    public void setSpeed_limiter_action_speed(String speed_limiter_action_speed) {
        this.speed_limiter_action_speed = speed_limiter_action_speed;
    }

    public String getDoor_motor_model() {
        return door_motor_model;
    }

    public void setDoor_motor_model(String door_motor_model) {
        this.door_motor_model = door_motor_model;
    }

    public String getCage_size() {
        return cage_size;
    }

    public void setCage_size(String cage_size) {
        this.cage_size = cage_size;
    }

    public String getSafety_gea_model() {
        return safety_gea_model;
    }

    public void setSafety_gea_model(String safety_gea_model) {
        this.safety_gea_model = safety_gea_model;
    }

    public String getWell_size() {
        return well_size;
    }

    public void setWell_size(String well_size) {
        this.well_size = well_size;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getDoor_control_system_type() {
        return door_control_system_type;
    }

    public void setDoor_control_system_type(String door_control_system_type) {
        this.door_control_system_type = door_control_system_type;
    }

    public boolean isHave_power_failure_reflatfloor() {
        return have_power_failure_reflatfloor;
    }

    public void setHave_power_failure_reflatfloor(boolean have_power_failure_reflatfloor) {
        this.have_power_failure_reflatfloor = have_power_failure_reflatfloor;
    }

    public boolean isHave_energy_feedback_system() {
        return have_energy_feedback_system;
    }

    public void setHave_energy_feedback_system(boolean have_energy_feedback_system) {
        this.have_energy_feedback_system = have_energy_feedback_system;
    }

    public List<String> getStation_mapping_list() {
        return station_mapping_list;
    }

    public void setStation_mapping_list(List<String> station_mapping_list) {
        this.station_mapping_list = station_mapping_list;
    }

    public List<Integer> getBelong_org_charge_ppl() {
        return belong_org_charge_ppl;
    }

    public void setBelong_org_charge_ppl(List<Integer> belong_org_charge_ppl) {
        this.belong_org_charge_ppl = belong_org_charge_ppl;
    }

    public List<Integer> getMaintenance_ppl() {
        return maintenance_ppl;
    }

    public void setMaintenance_ppl(List<Integer> maintenance_ppl) {
        this.maintenance_ppl = maintenance_ppl;
    }

    public List<User> getMaintenance_ppl_detail() {
        return maintenance_ppl_detail;
    }

    public void setMaintenance_ppl_detail(List<User> maintenance_ppl_detail) {
        this.maintenance_ppl_detail = maintenance_ppl_detail;
    }
}
