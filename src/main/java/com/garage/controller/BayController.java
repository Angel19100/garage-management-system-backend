package com.garage.controller;
import com.garage.entity.Bay;
import com.garage.service.BayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;

@RestController 
@RequestMapping("/api/bays")
public class BayController {

    private final BayService service;

    public BayController(BayService service) {
        this.service = service;
    }


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Bay bay) {
        service.save(bay);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bay saved successfully");
    }

     @GetMapping("/all")
    public ResponseEntity<List<Bay>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Bay> bay = service.getById(id);

        if(bay.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The bay with that id does not exist");

        }
        return ResponseEntity.ok(bay.get());
    }
     @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody Bay updated) {

        String result = service.update(id, updated);

        if (result.equals("The bay with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String result = service.delete(id);

        if (result.equals("The bay with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/free")
    public ResponseEntity<List<Bay>> getFreeBays(){
        return ResponseEntity.ok(service.getFreeBays());
    }
}
