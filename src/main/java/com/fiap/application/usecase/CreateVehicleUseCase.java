package com.fiap.application.usecase;

import com.fiap.application.gateway.VehicleGateway;
import com.fiap.domain.entity.Vehicle;
import com.fiap.domain.repository.VehicleRepository;
import com.fiap.infrastructure.controller.dto.VehicleRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicleUseCase {

    private final VehicleGateway gateway;

    public CreateVehicleUseCase(VehicleGateway gateway) {

        this.gateway = gateway;
    }

    public Vehicle execute(VehicleRequest request) {
        Vehicle vehicle = new Vehicle(request.getMarca(), request.getModelo(), request.getAno(), request.getCor(), request.getPreco(), "DISPONIVEL");
        return gateway.save(vehicle);
    }
}
