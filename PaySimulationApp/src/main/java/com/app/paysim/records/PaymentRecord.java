package com.app.paysim.records;

public record PaymentRecord(
        Long paymentId,
        Long dealerId,
        Double amount,
        String method,
        String status)
{}
