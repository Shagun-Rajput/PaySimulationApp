package com.app.paysim.service.impl;


import com.app.paysim.entities.PaymentEntity;
import com.app.paysim.enums.PaymentStatus;
import com.app.paysim.records.PaymentRecord;
import com.app.paysim.repository.PaymentRepository;
import com.app.paysim.service.PaymentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
}
