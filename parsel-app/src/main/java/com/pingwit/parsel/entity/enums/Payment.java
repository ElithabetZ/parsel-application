package com.pingwit.parsel.entity.enums;

public enum Payment {
    IN_PROCESS("IN PROCESS"),
    PAID("PAID");

    private String payment;
    private Payment(String payment){
        this.payment = payment;
    }
    public String getPayment(){
        return payment;
    }
}
