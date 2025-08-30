package com.app.dvm.entities;

import com.app.dvm.enums.VehicleStatus;
import jakarta.persistence.*;


@Entity
@Table(name = "vehicles")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private Double price;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    // Many-to-One relationship with DealerEntity (Each vehicle is associated with one dealer)
    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = false)
    private DealerEntity dealerEntity;
    //constructors
    public VehicleEntity() {
    }
    public VehicleEntity(String model, Double price, VehicleStatus status, DealerEntity dealerEntity) {
        this.model = model;
        this.price = price;
        this.status = status;
        this.dealerEntity = dealerEntity;
    }
    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public DealerEntity getDealerEntity() {
        return dealerEntity;
    }

    public void setDealerEntity(DealerEntity dealerEntity) {
        this.dealerEntity = dealerEntity;
    }
}
