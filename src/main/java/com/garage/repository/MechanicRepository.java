package com.garage.repository;

import com.garage.entity.Bay;
import com.garage.entity.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface MechanicRepository extends JpaRepository<Mechanic, UUID> {
//C1 findings mechanics speciality
@Query("SELECT DISTINCT m FROM Mechanic m " + "JOIN m.specialities s " + "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
List<Mechanic> findBySpecialityName(@Param("name") String name);

// D4 moving mechanic to a certain bay
boolean existsByBay(Bay bay);
}
