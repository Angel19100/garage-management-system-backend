package com.garage.controller;

import com.garage.entity.JobCard;
import com.garage.service.JobCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.garage.entity.JobCardStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

@RestController 
@RequestMapping("/api/job-cards")

public class JobCardController {

    private final JobCardService service;

    public JobCardController(JobCardService service) {
        this.service = service;
    }
     //A2 and D1 : saving a jobcard with business rule validatiom
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody JobCard jobcard) {
       String result =  service.save(jobcard);

       if(result.equals("Job card date cannot be in the past")) {
        return ResponseEntity.badRequest().body(result);

       }
       if(result.equals("Mechanic already has a job card on that date")){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
       }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/all")
     public ResponseEntity<List<JobCard>> getALL() {
        return ResponseEntity.ok(service.getAll());
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional <JobCard> jobCard = service.getById(id);

       if (jobCard.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The job card with that id does not exist");
        }

        return ResponseEntity.ok(jobCard.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody JobCard updated) {

        String result = service.update(id, updated);

        if (result.equals("The job card with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String result = service.delete(id);

        if (result.equals("The job card with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    //B2 getting job by status
    @GetMapping("/by-status")
    public ResponseEntity<List<JobCard>> getJobCardByStatus(@RequestParam JobCardStatus status) {
        return ResponseEntity.ok(service.getJobCardByStatus(status));
    } 

    //B3 gtting job cards between dates
    @GetMapping("/between")
    public ResponseEntity<List<JobCard>> getJobCardsBetweenDates(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return ResponseEntity.ok(service.getJobCardsBetweenDates(startDate, endDate));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updatedStatus(@PathVariable UUID id, @RequestParam JobCardStatus status) {

        String result = service.updateStatus(id, status);

        if(result.equals("The job card with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if(result.startsWith("Can not change status from")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        if(result.equals("Job card pdated successfully")) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.ok(result);

    }

    
}