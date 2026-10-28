package com.garage.repository;

import com.garage.entity.Bay;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BayRepository extends JpaRepository<Bay, UUID> {
//C2 getting a free bay that has no mechanic
@Query("SELECT b FROM Bay b " + "WHERE NOT EXISTS (" + "SELECT m FROM Mechanic m WHERE m.bay = b) " + "ORDER BY b.bayNumber ASC")
List<Bay> findFreeBays();

} 
