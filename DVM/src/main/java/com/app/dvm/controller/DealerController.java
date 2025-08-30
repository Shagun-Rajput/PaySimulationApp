package com.app.dvm.controller;

import com.app.dvm.model.ApiResponse;
import com.app.dvm.records.DealerRecord;
import com.app.dvm.service.DealerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.app.dvm.constant.ApiURIs.*;
import static com.app.dvm.constant.Constants.*;

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
    public ResponseEntity<ApiResponse> getAllDealers() {
        return ResponseEntity.ok(new ApiResponse(MSG_FETCHED_DEALERS_SUCCESS,
                dealerService.getAllDealers(),
                INT_200));
    }
    //API to Get specific dealer by ID
    @GetMapping(BY_ID)
    public ResponseEntity<ApiResponse> getDealerById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(MSG_FETCHED_DEALER_SUCCESS,
                dealerService.getDealerById(id),
                INT_200));
    }
    //API to Create a new dealer
    @PostMapping
    public ResponseEntity<ApiResponse> createDealer(@RequestBody DealerRecord dealerRecord) {
        return ResponseEntity.ok(new ApiResponse(MSG_CREATE_DEALER_SUCCESS,
                dealerService.createDealer(dealerRecord),
                INT_200));
    }
    //API to Update an existing dealer
    @PutMapping(BY_ID)
    public ResponseEntity<ApiResponse> updateDealer(@PathVariable Long id,
                                                    @RequestBody DealerRecord dealerRecord) {
        return ResponseEntity.ok(new ApiResponse(MSG_UPDATE_DEALER_SUCCESS,
                dealerService.updateDealer(id, dealerRecord),
                INT_200));
    }
    //API to Delete an existing dealer
    @DeleteMapping(BY_ID)
    public ResponseEntity<ApiResponse> deleteDealer(@PathVariable Long id) {
        dealerService.deleteDealer(id);
        return ResponseEntity.ok(new ApiResponse(MSG_DELETE_DEALER_SUCCESS,
                null,
                INT_200));
    }
    /**************************************** END *******************************************/
}
