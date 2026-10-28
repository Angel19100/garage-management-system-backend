package com.garage.controller;

import com.garage.entity.Vehicle;
import com.garage.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping("/api/vehicles")
public class VehicleController {
     
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service; 
    }

    @PostMapping("/save")
    public ResponseEntity<String> save (@RequestBody Vehicle vehicle) {
        service.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vehicle saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Vehicle> vehicle = service.getById(id);

        if (vehicle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The vehicle with that id does not exist");
        }

        return ResponseEntity.ok(vehicle.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody Vehicle updated) {

        String result = service.update(id, updated);

        if (result.equals("The vehicle with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String result = service.delete(id);

        if (result.equals("The vehicle with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-vehicle-type")
    public ResponseEntity<List<Vehicle>> getVehiclesByType(@RequestParam String vehicleType) {
        return ResponseEntity.ok(service.getVehiclesByType(vehicleType));

    }

    
@GetMapping("/of-mechanic/{mechanicId}")
public ResponseEntity<?> getVehiclesOfMechanic(
        @PathVariable UUID mechanicId) {

    return service.getVehiclesOfMechanic(mechanicId)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity
                    .status(404)
                    .body("The mechanic with that id does not exist"));
}

}
