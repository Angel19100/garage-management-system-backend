package com.garage.service;

import com.garage.entity.JobCard;
import com.garage.entity.JobCardStatus;
import com.garage.repository.JobCardRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

@Service
public class JobCardService {
    
    private final JobCardRepository repository;

    public JobCardService(JobCardRepository repository) {
        this.repository = repository;
    }
     
    //D1 validating before saving

    public String save(JobCard jobCard) {
        //(D1)rule 1: rejecting dates before today

         //reject dates before today.
        if (jobCard.getEntryDate().isBefore(LocalDate.now())) {
            return "Job card date cannot be in the past";
        }
          //rule 2 rejecting another active booking for this mechanic and date
         boolean alreadyBooked =
                repository.existsByMechanic_IdAndEntryDateAndStatusNot(
                        jobCard.getMechanic().getId(),
                        jobCard.getEntryDate(),
                        JobCardStatus.CANCELLED
                );
                if (alreadyBooked) {
            return "Mechanic already has a job card on that date";
        }

                 // Rule 3: Every new job card must start as OPENED.
        jobCard.setStatus(JobCardStatus.OPENED);
        repository.save(jobCard);

        return "Job card saved successfully";
    }

    public List<JobCard> getAll() {
        return repository.findAll();
    }

    public Optional<JobCard> getById(UUID id) {
        return repository.findById(id);
    }

    public String update(UUID id, JobCard updated) {
        Optional<JobCard> result = repository.findById(id);

        if (result.isEmpty()) {
            return "The job card with that id does not exist";
        }

        JobCard jobCard = result.get();
        jobCard.setEntryDate(updated.getEntryDate());
        jobCard.setWorkToDo(updated.getWorkToDo());
        jobCard.setVehicle(updated.getVehicle());
        jobCard.setMechanic(updated.getMechanic());

        repository.save(jobCard);

        return "Job card updated successfully";
    }

    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            return "The job card with that id does not exist";
        }

        repository.deleteById(id);
        return "Job card deleted successfully";
    }
    

    //B2 Finding job card status
    public List<JobCard> getJobCardByStatus(JobCardStatus status) {
        return repository.findByStatusOrderByEntryDateAsc(status);

    }

    //B3 Job cards date range
    public List<JobCard> getJobCardsBetweenDates(LocalDate start, LocalDate end) {
        return repository.findByEntryDateBetweenOrderByEntryDateAsc(start, end);
    }

    //D2 job status transitions
    public String updateStatus(UUID id, JobCardStatus newStatus) {
        Optional<JobCard> result = repository.findById(id);

        if (result.isEmpty()) {
            return "The job card with that id does not exist";
        }

        JobCard jobCard = result.get();
        JobCardStatus currentStatus = jobCard.getStatus();

        boolean allowed = (currentStatus == JobCardStatus.OPENED && (newStatus == JobCardStatus.IN_PROGRESS || newStatus == JobCardStatus.CANCELLED)) || (currentStatus == JobCardStatus.IN_PROGRESS && (newStatus == JobCardStatus.FINISHED || newStatus == JobCardStatus.CANCELLED));

        if (!allowed) {
            return "Can not change status from " + currentStatus + " to " +  newStatus;
        }

        jobCard.setStatus(newStatus);
        repository.save(jobCard);

        return "Job Card updated successfully";
    }
        
}
