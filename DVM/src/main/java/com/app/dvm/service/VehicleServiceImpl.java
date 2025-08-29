package com.app.dvm.service;

import com.app.dvm.repository.VehicleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public final class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    public VehicleServiceImpl(@Lazy VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    //*****************************CRUD Operations for Vehicle Entity***************************/
    @Override
    public java.util.List<com.app.dvm.records.VehicleRecord> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleEntity -> new com.app.dvm.records.VehicleRecord(
                        vehicleEntity.getId(),
                        vehicleEntity.getModel(),
                        vehicleEntity.(),
                        vehicleEntity.getPricePerDay(),
                        vehicleEntity.isAvailable(),
                        vehicleEntity.isPremium()
                ))
                .toList();
    }
    @Override
public com.app.dvm.records.VehicleRecord getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleEntity -> new com.app.dvm.records.VehicleRecord(
                        vehicleEntity.getId(),
                        vehicleEntity.getMake(),
                        vehicleEntity.getModel(),
                        vehicleEntity.getYear(),
                        vehicleEntity.getPricePerDay(),
                        vehicleEntity.isAvailable(),
                        vehicleEntity.isPremium()
                ))
                .orElse(null);
    }
    @Override
    public com.app.dvm.records.VehicleRecord createVehicle(com.app.dvm.records.VehicleRecord vehicleRecord) {
        var vehicleEntity = new com.app.dvm.entity.VehicleEntity();
        vehicleEntity.setMake(vehicleRecord.make());
        vehicleEntity.setModel(vehicleRecord.model());
        vehicleEntity.setYear(vehicleRecord.year());
        vehicleEntity.setPricePerDay(vehicleRecord.pricePerDay());
        vehicleEntity.setAvailable(vehicleRecord.isAvailable());
        vehicleEntity.setPremium(vehicleRecord.isPremium());
        var savedEntity = vehicleRepository.save(vehicleEntity);
        return new com.app.dvm.records.VehicleRecord(
                savedEntity.getId(),
                savedEntity.getMake(),
                savedEntity.getModel(),
                savedEntity.getYear(),
                savedEntity.getPricePerDay(),
                savedEntity.isAvailable(),
                savedEntity.isPremium()
        );
    }
    @Override
    public com.app.dvm.records.VehicleRecord updateVehicle(Long id, com.app.dvm.records.VehicleRecord vehicleRecord) {
        return vehicleRepository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setMake(vehicleRecord.make());
                    existingEntity.setModel(vehicleRecord.model());
                    existingEntity.setYear(vehicleRecord.year());
                    existingEntity.setPricePerDay(vehicleRecord.pricePerDay());
                    existingEntity.setAvailable(vehicleRecord.isAvailable());
                    existingEntity.setPremium(vehicleRecord.isPremium());
                    var updatedEntity = vehicleRepository.save(existingEntity);
                    return new com.app.dvm.records.VehicleRecord(
                            updatedEntity.getId(),
                            updatedEntity.getMake(),
                            updatedEntity.getModel(),
                            updatedEntity.getYear(),
                            updatedEntity.getPricePerDay(),
                            updatedEntity.isAvailable(),
                            updatedEntity.isPremium()
                    );
                })
                .orElse(null);
    }
    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
    @Override
    public java.util.List<com.app.dvm.records.VehicleRecord> getPremiumVehicles() {
        return vehicleRepository.findByIsPremiumTrue()
                .stream()
                .map(vehicleEntity -> new com.app.dvm.records.VehicleRecord(
                        vehicleEntity.getId(),
                        vehicleEntity.getMake(),
                        vehicleEntity.getModel(),
                        vehicleEntity.getYear(),
                        vehicleEntity.getPricePerDay(),
                        vehicleEntity.isAvailable(),
                        vehicleEntity.isPremium()
                ))
                .toList();
    }
    //**************************************** END *******************************************/
}
