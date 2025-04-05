package com.fiap.infrastructure.persistence;

import com.fiap.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaVehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByStatusOrderByPrecoAsc(String status);
}
