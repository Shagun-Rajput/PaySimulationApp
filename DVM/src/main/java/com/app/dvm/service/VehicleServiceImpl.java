package com.app.dvm.service;

import com.app.dvm.entities.VehicleEntity;
import com.app.dvm.enums.SubscriptionType;
import com.app.dvm.records.VehicleRecord;
import com.app.dvm.repository.VehicleRepository;
import com.app.dvm.repository.DealerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public final class VehicleServiceImpl implements VehicleService {
    /**
     * CRUD Operations for Vehicle Entity.
     */
    private final VehicleRepository vehicleRepository;
    private final DealerRepository dealerRepository;
    public VehicleServiceImpl(@Lazy VehicleRepository vehicleRepository,
                              @Lazy DealerRepository dealerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.dealerRepository = dealerRepository;
    }
    /*****************************CRUD Operations for Vehicle Entity***************************/
    /**
     * Get all vehicles.
     * Steps:
     * 1. Retrieve all vehicle entities from the repository using the `findAll` method.
     * 2. Use Java Streams to process the list of vehicle entities.
     * 3. For each vehicle entity, create a new `VehicleRecord` by mapping its fields.
     *    - Map the vehicle's ID, model, price, status, and associated dealer ID.
     * 4. Collect the mapped `VehicleRecord` objects into a list.
     * 5. Return the list of `VehicleRecord` objects.
     */
    @Override
    public List<VehicleRecord> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleEntity -> new VehicleRecord(
                        vehicleEntity.getId(),
                        vehicleEntity.getModel(),
                        vehicleEntity.getPrice(),
                        vehicleEntity.getStatus(),
                        vehicleEntity.getDealerEntity().getId()
                ))
                .toList();
    }
    /**
     * Get a vehicle by its ID.
     * Steps:
     * 1. Use the `findById` method of the repository to retrieve the vehicle entity by its ID.
     * 2. If the vehicle entity is found, map its fields to a new `VehicleRecord`.
     *    - Map the vehicle's ID, model, price, status, and associated dealer ID.
     * 3. If the vehicle entity is not found, throw a `RuntimeException` indicating that the vehicle was not found.
     * 4. Return the `VehicleRecord` object.
     */
    @Override
    public VehicleRecord getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleEntity -> new VehicleRecord(
                        vehicleEntity.getId(),
                        vehicleEntity.getModel(),
                        vehicleEntity.getPrice(),
                        vehicleEntity.getStatus(),
                        vehicleEntity.getDealerEntity().getId()
                ))
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
    }
    /**
     * Create a new vehicle.
     * Steps:
     * 1. Retrieve the dealer entity associated with the vehicle using the dealer ID from the `VehicleRecord`.
     *    - If the dealer is not found, throw a `RuntimeException`.
     * 2. Create a new `VehicleEntity` using the details from the `VehicleRecord` and the retrieved dealer entity.
     * 3. Save the new vehicle entity to the repository using the `save` method.
     * 4. Map the saved vehicle entity's fields to a new `VehicleRecord`.
     *    - Map the vehicle's ID, model, price, status, and associated dealer ID.
     * 5. Return the newly created `VehicleRecord`.
     */
    @Override
    public VehicleRecord createVehicle(VehicleRecord vehicleRecord) {
        var dealerEntity = dealerRepository.findById(vehicleRecord.dealerId())
                .orElseThrow(() -> new RuntimeException("Dealer not found with ID: " + vehicleRecord.dealerId()));

        var vehicleEntity = new VehicleEntity(
                vehicleRecord.model(),
                vehicleRecord.price(),
                vehicleRecord.status(),
                dealerEntity
        );

        var savedEntity = vehicleRepository.save(vehicleEntity);

        return new VehicleRecord(
                savedEntity.getId(),
                savedEntity.getModel(),
                savedEntity.getPrice(),
                savedEntity.getStatus(),
                savedEntity.getDealerEntity().getId()
        );
    }
    /**
     * Update an existing vehicle.
     * Steps:
     * 1. Retrieve the dealer entity associated with the vehicle using the dealer ID from the `VehicleRecord`.
     *    - If the dealer is not found, throw a `RuntimeException`.
     * 2. Use the `findById` method of the repository to retrieve the existing vehicle entity by its ID.
     * 3. If the vehicle entity is found, update its fields with the details from the `VehicleRecord` and the retrieved dealer entity.
     *    - Update the vehicle's model, price, status, and associated dealer entity.
     * 4. Save the updated vehicle entity to the repository using the `save` method.
     * 5. Map the updated vehicle entity's fields to a new `VehicleRecord`.
     *    - Map the vehicle's ID, model, price, status, and associated dealer ID.
     * 6. If the vehicle entity is not found, throw a `RuntimeException` indicating that the vehicle was not found.
     * 7. Return the updated `VehicleRecord`.
     */
    @Override
    public VehicleRecord updateVehicle(Long id, VehicleRecord vehicleRecord) {
        var dealerEntity = dealerRepository.findById(vehicleRecord.dealerId())
                .orElseThrow(() -> new RuntimeException("Dealer not found with ID: " + vehicleRecord.dealerId()));

        return vehicleRepository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setModel(vehicleRecord.model());
                    existingEntity.setPrice(vehicleRecord.price());
                    existingEntity.setStatus(vehicleRecord.status());
                    existingEntity.setDealerEntity(dealerEntity);

                    var updatedEntity = vehicleRepository.save(existingEntity);

                    return new VehicleRecord(
                            updatedEntity.getId(),
                            updatedEntity.getModel(),
                            updatedEntity.getPrice(),
                            updatedEntity.getStatus(),
                            updatedEntity.getDealerEntity().getId()
                    );
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
    }
    /**
     * Delete a vehicle by its ID.
     * Steps:
     * 1. Use the `deleteById` method of the repository to delete the vehicle entity by its ID.
     * 2. If the vehicle entity with the specified ID does not exist, the method will complete without throwing an exception.
     */
    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
    /**
     * Get all premium vehicles.
     * Steps:
     * 1. Use a custom repository method `findVehiclesByDealerSubscriptionType` to retrieve vehicle entities associated with dealers having a PREMIUM subscription type.
     * 2. Use Java Streams to process the list of retrieved vehicle entities.
     * 3. For each vehicle entity, create a new `VehicleRecord` by mapping its fields.
     *    - Map the vehicle's ID, model, price, status, and associated dealer ID.
     * 4. Collect the mapped `VehicleRecord` objects into a list.
     * 5. Return the list of `VehicleRecord` objects representing premium vehicles.
     */
    @Override
    public List<VehicleRecord> getPremiumVehicles() {
        return vehicleRepository.findVehiclesByDealerSubscriptionType(SubscriptionType.PREMIUM)
                .stream()
                .map(vehicleEntity -> new VehicleRecord(
                        vehicleEntity.getId(),
                        vehicleEntity.getModel(),
                        vehicleEntity.getPrice(),
                        vehicleEntity.getStatus(),
                        vehicleEntity.getDealerEntity().getId()
                ))
                .toList();
    }
    /**************************************** END *******************************************/
}