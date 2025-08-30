package com.app.paysim.service.impl;


import com.app.paysim.entities.PaymentEntity;
import com.app.paysim.enums.PaymentStatus;
import com.app.paysim.records.PaymentRecord;
import com.app.paysim.repository.PaymentRepository;
import com.app.paysim.service.PaymentService;
import com.app.paysim.specification.PaymentSpecification;
import com.app.paysim.utility.PaymentSimulateUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentSimulateUtil paymentSimulateUtil;
    public PaymentServiceImpl(@Lazy PaymentRepository paymentRepository,
                              @Lazy PaymentSimulateUtil paymentSimulateUtil) {
        this.paymentRepository = paymentRepository;
        this.paymentSimulateUtil = paymentSimulateUtil;
    }

    /**
     * Initiate Payment: Saves a new payment record with PENDING status.
     */
    @Override
    public PaymentRecord initiatePayment(PaymentRecord paymentRecord) {
        // Step-1 : Convert PaymentRecord to PaymentEntity
        PaymentEntity paymentEntity = convertToEntity(paymentRecord);
        // Step-2 :Save PaymentEntity to the database
        PaymentEntity savedEntity = savePayment(paymentEntity);
        // Step-3 :Simulate callback to update status after 5 seconds
        paymentSimulateUtil.simulateCallback(savedEntity.getId());
        // Step-4 :Convert saved PaymentEntity back to PaymentRecord and return
        return convertToRecord(savedEntity);
    }

    // Convert PaymentRecord to PaymentEntity
    private PaymentEntity convertToEntity(PaymentRecord paymentRecord) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setDealerId(paymentRecord.dealerId());
        paymentEntity.setAmount(paymentRecord.amount());
        paymentEntity.setMethod(paymentRecord.method());
        paymentEntity.setStatus(PaymentStatus.PENDING.value());
        return paymentEntity;
    }
    // Save PaymentEntity to the database
    private PaymentEntity savePayment(PaymentEntity paymentEntity) {
        return paymentRepository.save(paymentEntity);
    }

    // Convert PaymentEntity to PaymentRecord
    private PaymentRecord convertToRecord(PaymentEntity paymentEntity) {
        return new PaymentRecord(
                paymentEntity.getId(),
                paymentEntity.getDealerId(),
                paymentEntity.getAmount(),
                paymentEntity.getMethod(),
                paymentEntity.getStatus()
        );
    }
    /**
     * Find Payments with filtering and pagination.
     */
    @Override
    public Page<PaymentEntity> findPayments(PaymentRecord paymentRecord, int page, int size) {
        return paymentRepository.findAll(
                        PaymentSpecification.filterByPaymentRecord(
                                paymentRecord.paymentId(),
                                paymentRecord.dealerId(),
                                paymentRecord.amount(),
                                paymentRecord.method(),
                                paymentRecord.status()
                        ),
                        PageRequest.of(page, size)
                );
    }
}
