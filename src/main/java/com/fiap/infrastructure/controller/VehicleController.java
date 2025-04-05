package com.fiap.infrastructure.controller;

import com.fiap.application.usecase.*;
import com.fiap.domain.entity.Vehicle;
import com.fiap.infrastructure.controller.dto.VehicleRequest;
import com.fiap.infrastructure.controller.dto.VehicleResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final CreateVehicleUseCase createVehicleUseCase;
    private final UpdateVehicleUseCase updateVehicleUseCase;
    private final ListAvailableVehiclesUseCase listAvailableVehiclesUseCase;
    private final ListSoldVehiclesUseCase listSoldVehiclesUseCase;
    private final SellVehicleUseCase sellVehicleUseCase;

    public VehicleController(
            CreateVehicleUseCase createVehicleUseCase, UpdateVehicleUseCase updateVehicleUseCase,
            ListAvailableVehiclesUseCase listAvailableVehiclesUseCase, ListSoldVehiclesUseCase listSoldVehiclesUseCase, SellVehicleUseCase sellVehicleUseCase) {
        this.createVehicleUseCase = createVehicleUseCase;
        this.updateVehicleUseCase = updateVehicleUseCase;
        this.listAvailableVehiclesUseCase = listAvailableVehiclesUseCase;
        this.listSoldVehiclesUseCase = listSoldVehiclesUseCase;
        this.sellVehicleUseCase = sellVehicleUseCase;
    }


    @PostMapping
    public VehicleResponse create(@RequestBody @Valid VehicleRequest request) {
        Vehicle vehicle = createVehicleUseCase.execute(request);
        return toResponse(vehicle);
    }

    @GetMapping("/available")
    public List<VehicleResponse> listAvailableVehicles() {
        return listAvailableVehiclesUseCase.execute()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/sold")
    public List<VehicleResponse> listSoldVehicles() {
        return listSoldVehiclesUseCase.execute()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Marcar veículo como vendido
    @PutMapping("/{id}/sell")
    public VehicleResponse sellVehicle(@PathVariable Long id) {
        Vehicle vehicle = sellVehicleUseCase.execute(id);
        return toResponse(vehicle);
    }

    // Atualizar veículo
    @PutMapping("/{id}")
    public VehicleResponse update(@PathVariable Long id, @RequestBody @Valid VehicleRequest request) {
        Vehicle vehicle = updateVehicleUseCase.execute(id, request);
        return toResponse(vehicle);
    }

    private VehicleResponse toResponse(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse();
        response.setId(vehicle.getId());
        response.setMarca(vehicle.getMarca());
        response.setModelo(vehicle.getModelo());
        response.setAno(vehicle.getAno());
        response.setCor(vehicle.getCor());
        response.setPreco(vehicle.getPreco());
        response.setStatus(vehicle.getStatus());
        return response;
    }
}
