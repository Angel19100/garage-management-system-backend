package com.garage.repository;

import com.garage.entity.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JobCardRepository extends JpaRepository<JobCard, UUID> {
    
}
