package com.app.dvm.records;

import com.app.dvm.enums.VehicleStatus;

public record VehicleRecord(
        Long id,
        String model,
        Double price,
        VehicleStatus status,
        Long dealerId
) {}
