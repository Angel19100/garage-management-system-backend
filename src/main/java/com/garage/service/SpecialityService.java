package com.garage.service;

import com.garage.entity.Speciality;
import com.garage.repository.SpecialityRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpecialityService {
    public final SpecialityRepository repository;

    public SpecialityService(SpecialityRepository repository){
        this.repository=repository;
    }
    
    public String save(Speciality speciality){
        repository.save(speciality);
        return "Speciality saved successfully";    
    }

    public List<Speciality> getAll(){
        return repository.findAll();
    }

    public Optional<Speciality> getById(UUID id){
        return repository.findById(id);
    }

    public String update(UUID id, Speciality updated) {
        Optional<Speciality> result = repository.findById(id);
        
        if (result.isEmpty()){
            return "The specialty with that id does not exist";
        }

        Speciality speciality = result.get();
        speciality.setName(updated.getName());
        repository.save(speciality);
        return "Speciality updated successfully";

    }

    public String delete(UUID id){
        if(!repository.existsById(id)){
            return "The specialty with that id does not exist";
        }
        repository.deleteById(id);
        return "Speciality deleted successfully";
    }

    //C3 specialty nobody holds
    public List<Speciality> getUnusedSpecialities() {
        return repository.findUnsedSpecialities();

    }
}