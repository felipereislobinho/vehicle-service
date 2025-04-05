package com.fiap.infrastructure.persistence;

import com.fiap.domain.entity.Vehicle;
import com.fiap.domain.repository.VehicleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    private final JpaVehicleRepository jpaRepository;

    public VehicleRepositoryImpl(JpaVehicleRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return jpaRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Vehicle> findAvailableVehicles() {
        return jpaRepository.findByStatusOrderByPrecoAsc("DISPONIVEL");
    }

    @Override
    public List<Vehicle> findSoldVehicles() {
        return jpaRepository.findByStatusOrderByPrecoAsc("VENDIDO");
    }

}
