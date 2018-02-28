package com.zyjd.tijia.entity;

import java.util.List;

public class ElevatorRealtimeData {
    private int id;
    private String name;
    private int station;
    private String base_station;
    private List<String> station_mapping_list;
    private String sn;
    private int area;
    private Area area_detail;
    private RealtimeData realtime_data;

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

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public String getBase_station() {
        return base_station;
    }

    public void setBase_station(String base_station) {
        this.base_station = base_station;
    }

    public List<String> getStation_mapping_list() {
        return station_mapping_list;
    }

    public void setStation_mapping_list(List<String> station_mapping_list) {
        this.station_mapping_list = station_mapping_list;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Area getArea_detail() {
        return area_detail;
    }

    public void setArea_detail(Area area_detail) {
        this.area_detail = area_detail;
    }

    public RealtimeData getRealtime_data() {
        return realtime_data;
    }

    public void setRealtime_data(RealtimeData realtime_data) {
        this.realtime_data = realtime_data;
    }

    public static class RealtimeData {
        private String direction;
        private String carTemp;
        private String door;
        private String doorLock;
        private String runStatus;
        private String havePpl;
        private String elevatorLock;
        private String faultCode;
        private String station;
        private String floor;
        private String isOverload;
        private String carHum;
        private String sysPower;
        private String flatFloorStatus;
        private String brake;
        private String safetyCircuit;
        private String operationMode;
        private String timestamp;
        private String server_timestamp;
        private String ip;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getCarTemp() {
            return carTemp;
        }

        public void setCarTemp(String carTemp) {
            this.carTemp = carTemp;
        }

        public String getDoor() {
            return door;
        }

        public void setDoor(String door) {
            this.door = door;
        }

        public String getDoorLock() {
            return doorLock;
        }

        public void setDoorLock(String doorLock) {
            this.doorLock = doorLock;
        }

        public String getRunStatus() {
            return runStatus;
        }

        public void setRunStatus(String runStatus) {
            this.runStatus = runStatus;
        }

        public String getHavePpl() {
            return havePpl;
        }

        public void setHavePpl(String havePpl) {
            this.havePpl = havePpl;
        }

        public String getIsOverload() {
            return isOverload;
        }

        public void setIsOverload(String isOverload) {
            this.isOverload = isOverload;
        }

        public String getElevatorLock() {
            return elevatorLock;
        }

        public void setElevatorLock(String elevatorLock) {
            this.elevatorLock = elevatorLock;
        }

        public String getFaultCode() {
            return faultCode;
        }

        public void setFaultCode(String faultCode) {
            this.faultCode = faultCode;
        }

        public String getStation() {
            return station;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getCarHum() {
            return carHum;
        }

        public void setCarHum(String carHum) {
            this.carHum = carHum;
        }

        public String getSysPower() {
            return sysPower;
        }

        public void setSysPower(String sysPower) {
            this.sysPower = sysPower;
        }

        public String getFlatFloorStatus() {
            return flatFloorStatus;
        }

        public void setFlatFloorStatus(String flatFloorStatus) {
            this.flatFloorStatus = flatFloorStatus;
        }

        public String getBrake() {
            return brake;
        }

        public void setBrake(String brake) {
            this.brake = brake;
        }

        public String getSafetyCircuit() {
            return safetyCircuit;
        }

        public void setSafetyCircuit(String safetyCircuit) {
            this.safetyCircuit = safetyCircuit;
        }

        public String getOperationMode() {
            return operationMode;
        }

        public void setOperationMode(String operationMode) {
            this.operationMode = operationMode;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getServer_timestamp() {
            return server_timestamp;
        }

        public void setServer_timestamp(String server_timestamp) {
            this.server_timestamp = server_timestamp;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }
}
