package com.app.paysim.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @RequestMapping("/api/payment/initiate")
    public String healthCheck() {
        return "Payment Simulation Application is running...";
    }
}
