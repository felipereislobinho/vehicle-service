package com.fiap.application.usecase;

import com.fiap.application.gateway.VehicleGateway;
import com.fiap.domain.entity.Vehicle;
import com.fiap.domain.repository.VehicleRepository;
import com.fiap.infrastructure.exception.BusinessException;
import com.fiap.infrastructure.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SellVehicleUseCase {


    private final VehicleGateway gateway;

    public SellVehicleUseCase(VehicleGateway gateway) {
        this.gateway = gateway;

    }

    public Vehicle execute(Long id) {
        Vehicle vehicle = gateway.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado com id: " + id));
        if ("VENDIDO".equalsIgnoreCase(vehicle.getStatus())) {
            throw new BusinessException("Veículo já foi vendido");
        }

        vehicle.setStatus("VENDIDO");

        return gateway.save(vehicle);
    }
}