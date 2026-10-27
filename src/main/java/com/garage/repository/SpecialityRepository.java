package com.garage.repository;

import com.garage.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpecialityRepository extends JpaRepository<Speciality, UUID> {
}
