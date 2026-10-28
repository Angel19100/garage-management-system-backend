package com.garage.repository;

import com.garage.entity.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import com.garage.entity.JobCardStatus;
import java.time.LocalDate;

public interface JobCardRepository extends JpaRepository<JobCard, UUID> {
    //B2 getting jobcard status
    List<JobCard> findByStatusOrderByEntryDateAsc(JobCardStatus status);


    //B3 jobcards between two dates
    List<JobCard> findByEntryDateBetweenOrderByEntryDateAsc(LocalDate start, LocalDate end);
}
