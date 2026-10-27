package com.garage.controller;

import com.garage.entity.Speciality;
import com.garage.service.SpecialityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/specialities")
public class SpecialityController {
  
    private final SpecialityService service;

    public SpecialityController(SpecialityService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Speciality speciality) {
        service.save(speciality);
        return ResponseEntity.status(HttpStatus.CREATED).body("Speciality saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Speciality>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Speciality> speciality = service.getById(id);

        if (speciality.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The specialty with that id soes not exist") ;
        }

        return ResponseEntity.ok(speciality.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
        @PathVariable UUID id,
        @RequestBody Speciality updated) {
            String result = service.update(id, updated);

            if (result.equals("The specialty with that id does not exist")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }

            return ResponseEntity.ok(result);
        }


        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> delete(@PathVariable UUID id) {
            String result = service.delete(id);

            if(result.equals("The specialty with that id does not exist")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        }
    
        

 

}
