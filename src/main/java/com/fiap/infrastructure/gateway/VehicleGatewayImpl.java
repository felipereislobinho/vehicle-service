package com.fiap.infrastructure.gateway;

import com.fiap.application.gateway.VehicleGateway;
import com.fiap.domain.entity.Vehicle;
import com.fiap.domain.repository.VehicleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehicleGatewayImpl implements VehicleGateway {

    private final VehicleRepository repository;

    public VehicleGatewayImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Vehicle> findAvailableVehicles() {
        return repository.findAvailableVehicles();
    }

    @Override
    public List<Vehicle> findSoldVehicles() {
        return repository.findSoldVehicles();
    }
}