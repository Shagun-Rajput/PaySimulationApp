package com.app.dvm.entities;


import com.app.dvm.enums.SubscriptionType;
import com.app.dvm.records.DealerRecord;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "dealers")
@DynamicUpdate
@DynamicInsert
public class DealerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dealer_id")
    private Long id;
    @Column(name = "dealer_name")
    private String name;
    @Column(name = "dealer_email")
    private String email;

    @Column(name = "subscription_type")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
    //constructors
    public DealerEntity() {
    }
    public DealerEntity(Long id, String name, String email, SubscriptionType subscriptionType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subscriptionType = subscriptionType;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
    // Method to convert entity to record
    public DealerRecord toRecord() {
        return new DealerRecord(
                this.id,
                this.name,
                this.email,
                this.subscriptionType
        );
    }
}
