package com.garage.repository;

import com.garage.entity.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import com.garage.entity.JobCardStatus;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

public interface JobCardRepository extends JpaRepository<JobCard, UUID> {
    //B2 getting jobcard status
    List<JobCard> findByStatusOrderByEntryDateAsc(JobCardStatus status);


    //B3 jobcards between two dates
    List<JobCard> findByEntryDateBetweenOrderByEntryDateAsc(LocalDate start, LocalDate end);

    //D1: Check for a non cancelled jobcard for the mechanic on this date(safe saving)
    boolean existsByMechanic_IdAndEntryDateAndStatusNot(UUID mechanicId, LocalDate entryDate, JobCardStatus status);

    //F1: Count job Cards grouped by status
    @Query("SELECT j.status, COUNT(j) FROM JobCard j GROUP BY j.status")
    List<Object[]> countJobCardsByStatus();

    //F2 cancel jobvcards for a mechanic on a specific date
    @Modifying 
    @Query("UPDATE JobCard j SET j.status = :newStatus " +      "WHERE j.mechanic.id = :mechanicId " + "AND j.entryDate = :date " +  "AND j.status <> :excludedStatus")
int cancelJobCardsForMechanicOnDate( @Param("mechanicId") UUID mechanicId, @Param("date") LocalDate date,  @Param("newStatus") JobCardStatus newStatus, @Param("excludedStatus") JobCardStatus excludedStatus); 

// Dashboard: count job cards for a particular date
long countByEntryDate(LocalDate entryDate);

// Dashboard: count job cards with a particular status
long countByStatus(JobCardStatus status);
}
