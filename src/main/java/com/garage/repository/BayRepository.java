package com.garage.repository;

import com.garage.entity.Bay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BayRepository extends JpaRepository<Bay, UUID> {

} 
