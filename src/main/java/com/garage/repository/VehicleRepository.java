package com.garage.repository;
import com.garage.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
 
    //B1 finding vehicles by type ignoring case
    
}
