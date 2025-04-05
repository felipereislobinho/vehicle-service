package com.fiap.application.usecase;

import com.fiap.application.gateway.VehicleGateway;
import com.fiap.domain.entity.Vehicle;
import com.fiap.domain.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSoldVehiclesUseCase {

    private final VehicleGateway gateway;
    public ListSoldVehiclesUseCase( VehicleGateway gateway) {
        this.gateway = gateway;

    }
    public List<Vehicle> execute() {
        return gateway.findSoldVehicles();
    }
}