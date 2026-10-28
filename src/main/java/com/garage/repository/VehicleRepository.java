package com.garage.repository;
import com.garage.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
 
    //B1 finding vehicles by type ignoring case
    List<Vehicle> findByVehicleTypeIgnoreCaseOrderByPlateNumberAsc(String vehicleType);

    //C4 finding vehicles associated with mechanics

    
@Query("SELECT DISTINCT j.vehicle FROM JobCard j " + "WHERE j.mechanic.id = :mechanicId")
    List<Vehicle> findVehiclesOfMechanic( @Param("mechanicId") UUID mechanicId);

}
