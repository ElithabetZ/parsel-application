package com.pingwit.parsel.entity.enums;

public enum Status {

    WAITING("WAITING TO BE SENT"),
    SENT("SENT"),
    ONTHEWAY("ON THE WAY"),
    DELIVERED("DELIVERED"),
    COLLECTED("COLLECTED");

    private String status;
    private Status(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }

}
