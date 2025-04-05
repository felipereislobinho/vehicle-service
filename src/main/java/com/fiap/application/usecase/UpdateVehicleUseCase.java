package com.fiap.application.usecase;

import com.fiap.application.gateway.VehicleGateway;
import com.fiap.domain.entity.Vehicle;
import com.fiap.domain.repository.VehicleRepository;
import com.fiap.infrastructure.controller.dto.VehicleRequest;
import com.fiap.infrastructure.exception.NotFoundException;
import org.springframework.stereotype.Service;
import com.fiap.infrastructure.exception.BusinessException;


@Service
public class UpdateVehicleUseCase {

    private final VehicleGateway gateway;


    public UpdateVehicleUseCase(VehicleGateway gateway) {
        this.gateway = gateway;
    }

    public Vehicle execute(Long id, VehicleRequest request) {
        Vehicle existingVehicle = gateway.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado com id: " + id));
        if ("VENDIDO".equalsIgnoreCase(existingVehicle.getStatus())) {
            throw new BusinessException("Veículo vendido não permite alterações");
        }


        existingVehicle.setMarca(request.getMarca());
        existingVehicle.setModelo(request.getModelo());
        existingVehicle.setAno(request.getAno());
        existingVehicle.setCor(request.getCor());
        existingVehicle.setPreco(request.getPreco());

        return gateway.save(existingVehicle);
    }
}