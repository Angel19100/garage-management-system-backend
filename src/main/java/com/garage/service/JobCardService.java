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

    public String save(JobCard jobCard) {
       jobCard.setStatus(JobCardStatus.OPENED);
       repository.save(jobCard);
       return"Job card saved successfully";
    }

    public List<JobCard> getAll(){
        return repository.findAll();
    }

    public Optional<JobCard> getById(UUID id) {
        return repository.findById(id);
    }

    public String update(UUID id, JobCard updated){
        Optional<JobCard> result = repository.findById(id);

        if(result.isEmpty()) {
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
        if(!repository.existsById(id)) {
            return "The job card with that id does not exist";
        }

        repository.deleteById(id);
        return "Job card deleted succesfully";
    }

    //B2 Finding job card status
    public List<JobCard> getJobCardByStatus(JobCardStatus status) {
        return repository.findByStatusOrderByEntryDateAsc(status);

    }

    //B3 Job cards date range
    public List<JobCard> getJobCardsBetweenDates(LocalDate start, LocalDate end) {
        return repository.findByEntryDateBetweenOrderByEntryDateAsc(start, end);
    }
}
