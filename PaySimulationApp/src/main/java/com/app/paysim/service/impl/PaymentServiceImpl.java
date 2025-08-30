package com.app.paysim.service.impl;


import com.app.paysim.entities.PaymentEntity;
import com.app.paysim.enums.PaymentStatus;
import com.app.paysim.records.PaymentRecord;
import com.app.paysim.repository.PaymentRepository;
import com.app.paysim.service.PaymentService;
import com.app.paysim.specification.PaymentSpecification;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    public PaymentServiceImpl(@Lazy PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Initiate Payment: Saves a new payment record with PENDING status.
     */
    @Override
    public PaymentRecord initiatePayment(PaymentRecord paymentRecord) {
        // Convert PaymentRecord to PaymentEntity
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setDealerId(paymentRecord.dealerId());
        paymentEntity.setAmount(paymentRecord.amount());
        paymentEntity.setMethod(paymentRecord.method());
        paymentEntity.setStatus("PENDING");
        // Save PaymentEntity to the database
        PaymentEntity savedEntity = paymentRepository.save(paymentEntity);
        // Convert saved PaymentEntity back to PaymentRecord and return
        return new PaymentRecord(
                savedEntity.getId(),
                savedEntity.getDealerId(),
                savedEntity.getAmount(),
                savedEntity.getMethod(),
                savedEntity.getStatus()
        );
    }
    /**
     * Find Payments: Retrieves payment records based on provided filters.
     */
    @Override
    public List<PaymentRecord> findPayments(PaymentRecord paymentRecord) {
        return paymentRepository.findAll(
                        PaymentSpecification.filterByPaymentRecord(
                                paymentRecord.paymentId(),
                                paymentRecord.dealerId(),
                                paymentRecord.amount(),
                                paymentRecord.method(),
                                paymentRecord.status()
                        )
                ).stream()
                .map(entity -> new PaymentRecord(
                        entity.getId(),
                        entity.getDealerId(),
                        entity.getAmount(),
                        entity.getMethod(),
                        entity.getStatus()
                ))
                .toList();
    }
}
