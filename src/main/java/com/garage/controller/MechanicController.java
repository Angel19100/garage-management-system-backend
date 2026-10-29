package com.garage.controller;

import com.garage.entity.Mechanic;
import com.garage.service.MechanicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {

    private final MechanicService service;

    public MechanicController(MechanicService service){
         this.service = service;
    }


    @PostMapping("/save")
     public ResponseEntity<String> save(@RequestBody Mechanic mechanic) {
        service.save(mechanic);
        return ResponseEntity.status(HttpStatus.CREATED).body("Mechnanic saved successfully");
         
     }

     @GetMapping("/all")
     public ResponseEntity<List<Mechanic>> getAll(){
        return ResponseEntity.ok(service.getAll());
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Mechanic> mechanic = service.getById(id);

        if (mechanic.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The mechanic with that id does not exist");

        }
         return ResponseEntity.ok(mechanic.get());
     }

     @PutMapping("/update/{id}")
     public ResponseEntity<String> update(
        @PathVariable UUID id, @RequestBody Mechanic updated) {
            String result = service.update(id, updated);

            if (result.equals("The mechanic with that id does not exist")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }

            return ResponseEntity.ok(result);
        }

        @DeleteMapping("/delete/{id}")

        public ResponseEntity<String> delete (@PathVariable UUID id) {
            String result = service.delete(id);

             if (result.equals("The mechanic with that id does not exist")){
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
             }
             return ResponseEntity.ok(result);

                            
      } 
      @GetMapping("/by-speciality")
      public ResponseEntity<List<Mechanic>> getMechanicsBySpeciality(@RequestParam String name) {
        return ResponseEntity.ok(service.getMechanicsBySpeciality(name));
      }

      //D3 adding speciality to mechanic but with certain rules

      @PutMapping("/{mechanicId}/specialities/{specialityId}")
public ResponseEntity<String> addSpeciality(
        @PathVariable UUID mechanicId,
        @PathVariable UUID specialityId) {

    String result = service.addSpeciality(mechanicId, specialityId);

    if (result.equals("The mechanic with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("That specialty does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("The mechanic already has that specialty")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.ok(result);
}
//// D4 moving mechanic to a certain bay
@PatchMapping("/{mechanicId}/bay")
public ResponseEntity<String> changeBay(
        @PathVariable UUID mechanicId,
        @RequestParam Integer bayNumber) {

    String result = service.changeBay(mechanicId, bayNumber);

    if (result.equals("The mechanic with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("The bay with that bayNumber does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("That bay is taken by another mechanic")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.ok(result);
}

    

}
