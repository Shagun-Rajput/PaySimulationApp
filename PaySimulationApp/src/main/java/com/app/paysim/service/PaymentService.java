package com.app.paysim.service;

import com.app.paysim.records.PaymentRecord;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public interface PaymentService {
    PaymentRecord initiatePayment(PaymentRecord paymentRecord);
}
