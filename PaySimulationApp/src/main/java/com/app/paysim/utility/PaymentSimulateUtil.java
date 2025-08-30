package com.app.paysim.utility;

import com.app.paysim.enums.PaymentStatus;
import com.app.paysim.repository.PaymentRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class PaymentSimulateUtil {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(PaymentSimulateUtil.class);
    @Value("${app.simulation.default-delay:5000}")
    private long callbackDelay;
    private final PaymentRepository paymentRepository;
    public PaymentSimulateUtil(@Lazy PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    /*******************************************************************************************************************
     * Simulates a callback to update the payment status to "SUCCESS" after a delay of 5 seconds.
     *
     * @param paymentId The ID of the payment to be updated
     ******************************************************************************************************************/
    @Async
    public void simulateCallback(Long paymentId) {
        new Thread(() -> {
            try {
                logger.info("Simulating callback for Payment ID [{}] to update status to SUCCESS after [{}] seconds", paymentId, callbackDelay / 1000);
                Thread.sleep(callbackDelay); // Wait for defined duration
                paymentRepository.findById(paymentId).ifPresent(payment -> {
                    payment.setStatus(PaymentStatus.SUCCESS.value());
                    paymentRepository.save(payment);
                });
                logger.info("Payment ID [{}] status updated to SUCCESS", paymentId);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
