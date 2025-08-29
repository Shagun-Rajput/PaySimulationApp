package com.app.dvm.controller;

import com.app.dvm.records.DealerRecord;
import com.app.dvm.service.DealerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.dvm.constant.ApiURIs.BY_ID;
import static com.app.dvm.constant.ApiURIs.DEALERS;

@RestController
@RequestMapping(DEALERS)
public class DealerController {
    /**
     * Dependency Injection of DealerService to handle business logic.
     * The service is marked as @Lazy to prevent circular dependency issues.
     */
    private final DealerService dealerService;
    public DealerController(@Lazy DealerService dealerService) {
        this.dealerService = dealerService;
    }
    /*******************************************************************************************************************
     * CRUD Operations for Dealer Entity.
     *
     * API Endpoints:
     *
     * 1. GET /dealers
     *    - Description: Fetch all dealers.
     *    - Response: List of DealerRecord objects.
     *
     * 2. GET /dealers/{id}
     *    - Description: Fetch a specific dealer by ID.
     *    - Path Variable: id (Long) - ID of the dealer to fetch.
     *    - Response: DealerRecord object of the specified dealer.
     *
     * 3. POST /dealers
     *    - Description: Create a new dealer.
     *    - Request Body: DealerRecord object containing dealer details.
     *    - Response: DealerRecord object of the created dealer.
     *
     * 4. PUT /dealers/{id}
     *    - Description: Update an existing dealer.
     *    - Path Variable: id (Long) - ID of the dealer to update.
     *    - Request Body: DealerRecord object containing updated dealer details.
     *    - Response: DealerRecord object of the updated dealer.
     * 5. DELETE /dealers/{id}
     *   - Description: Delete an existing dealer.
     *   - Path Variable: id (Long) - ID of the dealer to delete.
     *   - Response: void
     *
     ******************************************************************************************************************/
    //API to Get all dealers
    @GetMapping
    public ResponseEntity<List<DealerRecord>> getAllDealers() {
        return ResponseEntity.ok(dealerService.getAllDealers());
    }
    //API to Get specific dealer by ID
    @GetMapping(BY_ID)
    public ResponseEntity<DealerRecord> getDealerById(@PathVariable Long id) {
        return ResponseEntity.ok(dealerService.getDealerById(id));
    }
    //API to Create a new dealer
    @PostMapping
    public ResponseEntity<DealerRecord> createDealer(@RequestBody DealerRecord dealerRecord) {
        return ResponseEntity.ok(dealerService.createDealer(dealerRecord));
    }
    //API to Update an existing dealer
    @PutMapping(BY_ID)
    public ResponseEntity<DealerRecord> updateDealer(@PathVariable Long id, @RequestBody DealerRecord dealerRecord) {
        return ResponseEntity.ok(dealerService.updateDealer(id, dealerRecord));
    }
    //API to Delete an existing dealer
    @DeleteMapping(BY_ID)
    public ResponseEntity<Void> deleteDealer(@PathVariable Long id) {
        dealerService.deleteDealer(id);
        return ResponseEntity.noContent().build();
    }
    /**************************************** END *******************************************/
}
