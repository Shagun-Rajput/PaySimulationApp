package com.app.paysim.enums;

public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS");

    PaymentStatus(String status) {
        this.status = status;
    }
    private String status;
    public String value() {
        return status;
    }
}
