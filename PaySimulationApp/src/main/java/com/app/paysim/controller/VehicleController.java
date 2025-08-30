package com.app.dvm.controller;

import com.app.dvm.model.ApiResponse;
import com.app.dvm.records.VehicleRecord;
import com.app.dvm.service.VehicleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.app.dvm.constant.ApiURIs.*;
import static com.app.dvm.constant.Constants.*;

@RestController
@RequestMapping(VEHICLES)
public class VehicleController {
    private final VehicleService vehicleService;
    public VehicleController(@Lazy VehicleService vehicleService) {

        this.vehicleService = vehicleService;
    }
    /**
     * CRUD Operations for Vehicle Entity.
     * API Endpoints:
     * 1. GET /vehicles
     *   - Description: Fetch all vehicles.
     *   - Response: List of VehicleRecord objects.
     * 2. GET /vehicles/{id}
     *  - Description: Fetch a specific vehicle by ID.
     *  - Path Variable: id (Long) - ID of the vehicle to fetch.
     *  - Response: VehicleRecord object of the specified vehicle.
     *  3. POST /vehicles
     *  - Description: Create a new vehicle.
     *  - Request Body: VehicleRecord object containing vehicle details.
     *  - Response: VehicleRecord object of the created vehicle.
     *  4. PUT /vehicles/{id}
     *  - Description: Update an existing vehicle.
     *  - Path Variable: id (Long) - ID of the vehicle to update.
     *  - Request Body: VehicleRecord object containing updated vehicle details.
     *  - Response: VehicleRecord object of the updated vehicle.
     *  5. DELETE /vehicles/{id}
     *  - Description: Delete an existing vehicle.
     *  - Path Variable: id (Long) - ID of the vehicle to delete.
     *  - Response: void
     *  6. get premium vehicles
     *  - Description: Fetch all premium vehicles.
     *  - Response: List of VehicleRecord objects marked as premium.
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getAllVehicles() {
        return ResponseEntity.ok(new ApiResponse(MSG_FETCHED_VEHICLES_SUCCESS,
                vehicleService.getAllVehicles(),
                INT_200));
    }
    @GetMapping(BY_ID)
    public ResponseEntity<ApiResponse> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(MSG_FETCHED_VEHICLE_SUCCESS,
                vehicleService.getVehicleById(id),
                INT_200));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createVehicle(@RequestBody VehicleRecord vehicleRecord) {
        return ResponseEntity.ok(new ApiResponse(MSG_CREATE_VEHICLE_SUCCESS,
                vehicleService.createVehicle(vehicleRecord),
                INT_200));
    }

    @PutMapping(BY_ID)
    public ResponseEntity<ApiResponse> updateVehicle(@PathVariable Long id, @RequestBody VehicleRecord vehicleRecord) {
        return ResponseEntity.ok(new ApiResponse(MSG_UPDATE_VEHICLE_SUCCESS,
                vehicleService.updateVehicle(id, vehicleRecord),
                INT_200));
    }

    @DeleteMapping(BY_ID)
    public ResponseEntity<ApiResponse> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok(new ApiResponse(MSG_DELETE_VEHICLE_SUCCESS,
                null,
                INT_200));
    }

    @GetMapping(PREMIUM_URI)
    public ResponseEntity<ApiResponse> getPremiumVehicles() {
        return ResponseEntity.ok(new ApiResponse(MSG_FETCHED_PREMIUM_VEHICLES_SUCCESS,
                vehicleService.getPremiumVehicles(),
                INT_200));
    }
    /**************************************** END *******************************************/
}
