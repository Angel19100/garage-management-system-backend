package com.garage.repository;

import com.garage.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
public interface SpecialityRepository extends JpaRepository<Speciality, UUID> {

    @Query("SELECT s FROM Speciality s " + "WHERE s.mechanics IS EMPTY " + "ORDER BY s.name ASC") 
    List<Speciality> findUnsedSpecialities();
}
