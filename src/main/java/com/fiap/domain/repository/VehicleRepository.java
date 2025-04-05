package com.fiap.domain.repository;

import com.fiap.domain.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    List<Vehicle> findAvailableVehicles();

    List<Vehicle> findSoldVehicles();


}
