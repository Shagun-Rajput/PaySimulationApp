package com.app.paysim.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "payments")
@DynamicInsert
@DynamicUpdate
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "dealer_id")
    private Long dealerId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "method")
    private String method;
    @Column(name = "status")
    private String status;
    //constructors
    public PaymentEntity() {
    }
    public PaymentEntity(Long id, Long dealerId, Double amount, String method, String status) {
        this.id = id;
        this.dealerId = dealerId;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }
    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
