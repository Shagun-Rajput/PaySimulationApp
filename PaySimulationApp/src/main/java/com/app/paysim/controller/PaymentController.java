package com.app.paysim.controller;

import com.app.paysim.entities.PaymentEntity;
import com.app.paysim.model.ApiResponse;
import com.app.paysim.records.PaymentRecord;
import com.app.paysim.service.PaymentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.paysim.constant.ApiURIs.URI_FIND_PAYMENTS;
import static com.app.paysim.constant.ApiURIs.URI_INIT_PAYMENT;
import static com.app.paysim.constant.Constants.*;

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
                INT_200, null
        ));
    }
    /**
     * Get Payment details with filters on each field
     */
    @GetMapping(URI_FIND_PAYMENTS)
    public ResponseEntity<ApiResponse> findPayments(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long dealerId,
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) String method,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Fetch paginated payment records based on filters
        Page<PaymentEntity> paymentPage = paymentService.findPayments(
                new PaymentRecord(id, dealerId, amount, method, status), page, size);
        // Map PaymentEntity to PaymentRecord
        List<PaymentRecord> records = paymentPage.getContent().stream()
                .map(entity -> new PaymentRecord(
                        entity.getId(),
                        entity.getDealerId(),
                        entity.getAmount(),
                        entity.getMethod(),
                        entity.getStatus()
                ))
                .toList();
        // Create metadata for pagination
        ApiResponse.Meta meta = new ApiResponse.Meta(
                paymentPage.getNumber(),
                paymentPage.getSize(),
                paymentPage.getTotalElements(),
                paymentPage.getTotalPages()
        );
        // Return response with records and metadata
        return ResponseEntity.ok(new ApiResponse(
                MSG_PAYMENTS_DETAILS_SUCCESS,
                records,
                INT_200,
                meta
        ));
    }
}
