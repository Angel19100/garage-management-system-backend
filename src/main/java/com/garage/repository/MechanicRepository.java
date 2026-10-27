package com.garage.repository;

import com.garage.entity.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MechanicRepository extends JpaRepository<Mechanic, UUID> {
}
