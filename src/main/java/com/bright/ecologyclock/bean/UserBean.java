package com.bright.ecologyclock.bean;

public class UserBean {
    private int id;
    private String name;
    private String password;
    private String role;
    private String ecologyCode;
    private String ecologyPasswd;
    private String ecologyAddress;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEcologyCode() {
        return ecologyCode;
    }

    public void setEcologyCode(String ecologyCode) {
        this.ecologyCode = ecologyCode;
    }

    public String getEccologyPasswd() {
        return ecologyPasswd;
    }

    public void setEcologyPasswd(String ecologyPasswd) {
        this.ecologyPasswd = ecologyPasswd;
    }

    public String getEcologyAddress() {
        return ecologyAddress;
    }

    public void setEcologyAddress(String ecologyAddress) {
        this.ecologyAddress = ecologyAddress;
    }
}
