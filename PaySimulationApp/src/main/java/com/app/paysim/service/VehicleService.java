package com.app.dvm.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
@Lazy
@Service
public interface VehicleService{
    //*****************************CRUD Operations for Vehicle Entity***************************/
    List<com.app.dvm.records.VehicleRecord> getAllVehicles();
    com.app.dvm.records.VehicleRecord getVehicleById(Long id);
    com.app.dvm.records.VehicleRecord createVehicle(com.app.dvm.records.VehicleRecord vehicleRecord);
    com.app.dvm.records.VehicleRecord updateVehicle(Long id, com.app.dvm.records.VehicleRecord vehicleRecord);
    void deleteVehicle(Long id);
    List<com.app.dvm.records.VehicleRecord> getPremiumVehicles();

    //**************************************** END *******************************************/
}
