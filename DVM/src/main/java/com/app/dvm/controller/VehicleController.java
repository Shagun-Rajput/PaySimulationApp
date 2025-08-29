package com.app.dvm.controller;

import com.app.dvm.records.VehicleRecord;
import com.app.dvm.service.VehicleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.dvm.constant.ApiURIs.VEHICLES;

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
    public ResponseEntity<List<VehicleRecord>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleRecord> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @PostMapping
    public ResponseEntity<VehicleRecord> createVehicle(@RequestBody VehicleRecord vehicleRecord) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicleRecord));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleRecord> updateVehicle(@PathVariable Long id, @RequestBody VehicleRecord vehicleRecord) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/premium")
    public ResponseEntity<List<VehicleRecord>> getPremiumVehicles() {
        return ResponseEntity.ok(vehicleService.getPremiumVehicles());
    }
    /**************************************** END *******************************************/
}
