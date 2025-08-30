package com.app.dvm.service.impl;


import com.app.dvm.records.DealerRecord;
import com.app.dvm.repository.DealerRepository;
import com.app.dvm.service.DealerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public class DealerServiceImpl implements DealerService {
    /**
     * Dependency Injection of DealerRepository to interact with the data layer.
     * The repository is marked as @Lazy to prevent circular dependency issues.
     */
    private final DealerRepository dealerRepository;
    public DealerServiceImpl(@Lazy DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }
    /*****************************CRUD Operations for Dealer Entity***************************/
    /**
     * Fetch all dealer entities from the repository.
     * Steps:
     * 1. Retrieve all dealer entities using the repository's `findAll` method.
     * 2. Use Java Streams to iterate over the list of entities.
     * 3. Map each entity to a `DealerRecord` by extracting its fields.
     * 4. Collect the mapped records into a list and return.
     */
    @Override
    public List<DealerRecord> getAllDealers() {
        return dealerRepository.findAll()
                .stream()
                .map(dealerEntity -> new DealerRecord(
                        dealerEntity.getId(),
                        dealerEntity.getName(),
                        dealerEntity.getEmail(),
                        dealerEntity.getSubscriptionType()
                ))
                .toList();
    }
    /**
     * Fetch a dealer entity by its ID from the repository.
     * Steps:
     * 1. Use the repository's `findById` method to retrieve the dealer entity.
     * 2. If the entity is found, map its fields to a `DealerRecord`.
     * 3. If the entity is not found, return null.
     */
    @Override
    public DealerRecord getDealerById(Long id) {
        return dealerRepository.findById(id)
                .map(dealerEntity -> new DealerRecord(
                        dealerEntity.getId(),
                        dealerEntity.getName(),
                        dealerEntity.getEmail(),
                        dealerEntity.getSubscriptionType()
                ))
                .orElse(null);
    }
    /**
     * Create a new dealer entity in the repository.
     * Steps:
     * 1. Construct a new `DealerEntity` using the fields from the provided `DealerRecord`.
     * 2. Save the new entity to the repository using the `save` method.
     * 3. Convert the saved entity back to a `DealerRecord` and return it.
     */
    @Override
    public DealerRecord createDealer(DealerRecord dealerRecord) {
        return dealerRepository.save(new com.app.dvm.entities.DealerEntity(
                        dealerRecord.dealerId(),
                        dealerRecord.dealerName(),
                        dealerRecord.dealerEmail(),
                        dealerRecord.subscriptionType()
                )).toRecord();
    }
    /**
     * Update an existing dealer entity in the repository.
     * Steps:
     * 1. Retrieve the existing dealer entity by its ID using the repository's `findById` method.
     * 2. If the entity exists, update its fields with the values from the provided `DealerRecord`.
     * 3. Save the updated entity back to the repository using the `save` method.
     * 4. Convert the saved entity back to a `DealerRecord` and return it.
     * 5. If the entity does not exist, return null.
     */
    @Override
    public DealerRecord updateDealer(Long id, DealerRecord dealerRecord) {
        return dealerRepository.findById(id)
                .map(existingDealer -> {
                    existingDealer.setName(dealerRecord.dealerName());
                    existingDealer.setEmail(dealerRecord.dealerEmail());
                    existingDealer.setSubscriptionType(dealerRecord.subscriptionType());
                    return dealerRepository.save(existingDealer).toRecord();
                })
                .orElse(null);
    }
    /**
     * Delete a dealer entity from the repository by its ID.
     * Steps:
     * 1. Use the repository's `deleteById` method to remove the dealer entity.
     * 2. This method does not return any value.
     */
    @Override
    public void deleteDealer(Long id) {
        dealerRepository.deleteById(id);
    }
    /**************************************** END *******************************************/
}
