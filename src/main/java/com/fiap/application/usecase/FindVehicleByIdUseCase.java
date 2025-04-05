package com.fiap.application.usecase;

import com.fiap.domain.entity.Vehicle;
import com.fiap.application.gateway.VehicleGateway;
import com.fiap.infrastructure.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindVehicleByIdUseCase {

    private final VehicleGateway gateway;

    public FindVehicleByIdUseCase(VehicleGateway gateway) {
        this.gateway = gateway;
    }

    public Vehicle execute(Long id) {
        return gateway.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado com o ID informado"));
    }
}