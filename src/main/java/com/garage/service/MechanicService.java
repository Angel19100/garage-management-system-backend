package com.garage.service;

import com.garage.entity.Mechanic;
import com.garage.repository.MechanicRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
    
@Service
public class MechanicService {

    private final MechanicRepository repository;

    public MechanicService(MechanicRepository repository) {
        this.repository = repository;

    }

    public String save(Mechanic mechanic) {
        repository.save(mechanic);
        return "Mechanic saved successfully";
    }

    public List<Mechanic> getAll(){
        return repository.findAll();
    }

    public Optional<Mechanic> getById(UUID id){
        return repository.findById(id);
    }

    public String update(UUID id, Mechanic updated) {
        Optional<Mechanic> result = repository.findById(id);

        if (result.isEmpty()) {
            return "The mechanic with that id does not exist";
        }

        Mechanic mechanic = result.get();
        mechanic.setFirstName(updated.getFirstName());
        mechanic.setLastName(updated.getLastName());
        mechanic.setPhone(updated.getPhone());
        mechanic.setBay(updated.getBay());
        mechanic.setSpecialities(updated.getSpecialities());

        repository.save(mechanic);

        return "Mechanic updated successfully";
    }

    
public String delete(UUID id) {
    if (!repository.existsById(id)) {
        return "The mechanic with that id does not exist";
    }

    repository.deleteById(id);
    return "Mechanic deleted successfully";
}




}
