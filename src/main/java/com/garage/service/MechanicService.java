package com.garage.service;

import com.garage.entity.Bay;
import com.garage.entity.Mechanic;
import com.garage.entity.Speciality;
import com.garage.repository.MechanicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

//D3 Adding mechanic speciality but with rules
@Transactional
public String addSpeciality(UUID mechanicId, UUID specialityId) {
    Optional<Mechanic> mechanicResult = repository.findById(mechanicId);

    if (mechanicResult.isEmpty()) {
        return "The mechanic with that id does not exist";
    }

    Optional<Speciality> specialityResult = specialityRepository.findById(specialityId);

    if (specialityResult.isEmpty()) {
        return "That specialty does not exist";
    }

    Mechanic mechanic = mechanicResult.get();
    Speciality speciality = specialityResult.get();

    if (mechanic.getSpecialities().contains(speciality)) {
        return "The mechanic already has that specialty";
    }

    mechanic.getSpecialities().add(speciality);
    repository.save(mechanic);

    return "Specialty added to the mechanic";
}
//D4 moving mechanic to a certain bay

public String changeBay(UUID mechanicId, Integer bayNumber) {
    Optional<Mechanic> mechanicResult =
            repository.findById(mechanicId);

    if (mechanicResult.isEmpty()) {
        return "The mechanic with that id does not exist";
    }

    Optional<Bay> bayResult =
            bayRepository.findByBayNumber(bayNumber);

    if (bayResult.isEmpty()) {
        return "The bay with that bayNumber does not exist";
    }

    Mechanic mechanic = mechanicResult.get();
    Bay newBay = bayResult.get();

    boolean alreadyInThisBay =
            mechanic.getBay() != null
            && mechanic.getBay().getId().equals(newBay.getId());

    if (!alreadyInThisBay && repository.existsByBay(newBay)) {
        return "That bay is taken by another mechanic";
    }

    mechanic.setBay(newBay);
    repository.save(mechanic);

    return "Bay changed successfully";
}


}
