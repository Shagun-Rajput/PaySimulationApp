package com.app.dvm.repository;


import com.app.dvm.entities.VehicleEntity;
import com.app.dvm.enums.SubscriptionType;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Lazy
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    /**
     * Find vehicles by the subscription type of their associated dealer.
     *
     * @param subscriptionType the subscription type of the dealer (e.g., BASIC, PREMIUM)
     * @return a collection of VehicleEntity objects associated with dealers having the specified subscription type
     */
    @Query("SELECT v FROM VehicleEntity v WHERE v.dealerEntity.subscriptionType = :subscriptionType")
    List<VehicleEntity> findVehiclesByDealerSubscriptionType(@Param("subscriptionType") SubscriptionType subscriptionType);
}
