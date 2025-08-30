package com.app.paysim.service;

import com.app.paysim.entities.PaymentEntity;
import com.app.paysim.records.PaymentRecord;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public interface PaymentService {
    PaymentRecord initiatePayment(PaymentRecord paymentRecord);

    Page<PaymentEntity> findPayments(PaymentRecord paymentRecord, int page, int size);
}
