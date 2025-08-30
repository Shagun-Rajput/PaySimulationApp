package com.app.paysim.controller;

import com.app.paysim.model.ApiResponse;
import com.app.paysim.records.PaymentRecord;
import com.app.paysim.service.PaymentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.app.paysim.constant.ApiURIs.URI_INIT_PAYMENT;
import static com.app.paysim.constant.Constants.INT_200;
import static com.app.paysim.constant.Constants.MSG_INITIATE_PAYMENT;

@RestController
public class PaymentController {
    private final  PaymentService paymentService;
    public PaymentController(@Lazy PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Initiate Payment API
     *
     */
    @PostMapping(URI_INIT_PAYMENT)
    public ResponseEntity<ApiResponse> initiatePayment(@RequestBody PaymentRecord paymentRecord) {
        return ResponseEntity.ok(new ApiResponse(
                MSG_INITIATE_PAYMENT,
                paymentService.initiatePayment(paymentRecord),
                INT_200
        ));
    }
}
