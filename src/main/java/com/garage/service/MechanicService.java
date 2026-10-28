package com.garage.service;

import com.garage.entity.Bay;
import com.garage.entity.Mechanic;
import com.garage.entity.Speciality;
import com.garage.repository.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.garage.repository.BayRepository;
import com.garage.repository.SpecialityRepository;

@Service
public class MechanicService {

    private final MechanicRepository repository;
    private final BayRepository bayRepository;
    private final SpecialityRepository specialityRepository;

    public MechanicService(MechanicRepository repository, BayRepository bayRepository, SpecialityRepository specialityRepository ) {
        this.repository = repository;
        this.bayRepository = bayRepository;
        this.specialityRepository= specialityRepository;

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
        
        if(updated.getBay() != null&& updated.getBay().getId() != null){
            Optional<Bay> bayResult = bayRepository.findById(updated.getBay().getId());

            if(bayResult.isEmpty()){
                return"The bay with that id does not exist";
            }
            mechanic.setBay(bayResult.get());
        }

        if(updated.getSpecialities() != null){
            List<Speciality> managedSpecialities = new ArrayList<>();

            for(Speciality speciality : updated.getSpecialities()){
                if(speciality.getId() == null) {
                                    return "A speciality must have an id";
                }
                Optional<Speciality> specialityResult = specialityRepository.findById(speciality.getId());

                if (specialityResult.isEmpty()) {
                    return "That speciality does not exist";
                }

                managedSpecialities.add(specialityResult.get());
            }

            mechanic.setSpecialities(managedSpecialities);
        }

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

//C1 mechanics by specialty
public List<Mechanic> getMechanicsBySpeciality(String name) {
    return repository.findBySpecialityName(name);
} 


}
