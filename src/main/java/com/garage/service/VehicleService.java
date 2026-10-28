package com.garage.service;

import com.garage.entity.Vehicle;
import com.garage.repository.MechanicRepository;
import com.garage.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class VehicleService {
    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public String save(Vehicle vehicle){
        repository.save(vehicle);
        return "Vehicle saved successfully";
    }

     public List<Vehicle> getAll() {
        return repository.findAll();
    }


    public Optional<Vehicle> getById(UUID id) {
        return repository.findById(id);
    }
    
    public String update(UUID id, Vehicle updated) {
        Optional<Vehicle> result = repository.findById(id);

        if(result.isEmpty()){
            return "The vehicle with that id does not exist";
        }

        Vehicle vehicle= result.get();
        vehicle.setMake(updated.getMake());
        vehicle.setModel(updated.getModel());
        vehicle.setPlateNumber(updated.getPlateNumber());
        vehicle.setOwnerName(updated.getOwnerName());
        vehicle.setVehicleType(updated.getVehicleType());

        repository.save(vehicle);

        return"Vehicle updated successfully";

    }

    public String delete (UUID id) {
        if(!repository.existsById(id)) {
            return"The vehicle with that id does not exist";
        }

        repository.deleteById(id);
        return "Vehicle deleted successfuly";
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return repository.findByVehicleTypeIgnoreCaseOrderByPlateNumberAsc(vehicleType);
    }

    
@Autowired
private MechanicRepository mechanicRepository;

public Optional<List<Vehicle>> getVehiclesOfMechanic(UUID mechanicId) {
    if (!mechanicRepository.existsById(mechanicId)) {
        return Optional.empty();
    }

    return Optional.of(repository.findVehiclesOfMechanic(mechanicId));
}


}
