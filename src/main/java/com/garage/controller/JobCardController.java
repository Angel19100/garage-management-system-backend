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

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody JobCard jobcard) {
        service.save(jobcard);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job card saved successfully");
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

    //B2 getting job status
    @GetMapping("/by-status")
    public ResponseEntity<List<JobCard>> getJobCardByStatus(@RequestParam JobCardStatus status) {
        return ResponseEntity.ok(service.getJobCardByStatus(status));
    } 

    @GetMapping("/between")
    public ResponseEntity<List<JobCard>> getJobCardsBetweenDates(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return ResponseEntity.ok(service.getJobCardsBetweenDates(startDate, endDate));
    }

    
}