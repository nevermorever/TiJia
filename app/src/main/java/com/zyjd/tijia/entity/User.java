package com.zyjd.tijia.entity;

import java.util.List;

public class User {
    private int id;
    private String username;
    private String name;
    private int org;
    private Org org_detail;
    private String gender;
    private String mobile;
    private String email;
    private String avatar;
    private boolean is_active;
    private boolean easymob_active;
    private List<String> roles;
    private List<Roles> roles_detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isEasymob_active() {
        return easymob_active;
    }

    public void setEasymob_active(boolean easymob_active) {
        this.easymob_active = easymob_active;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Roles> getRoles_detail() {
        return roles_detail;
    }

    public void setRoles_detail(List<Roles> roles_detail) {
        this.roles_detail = roles_detail;
    }

    public static class Roles {

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

