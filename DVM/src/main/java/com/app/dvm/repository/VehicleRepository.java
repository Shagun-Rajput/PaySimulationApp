package com.app.dvm.repository;


import com.app.dvm.entities.VehicleEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

}
