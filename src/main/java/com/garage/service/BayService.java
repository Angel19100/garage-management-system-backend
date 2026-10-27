
package com.garage.service;

import com.garage.entity.Bay;
import com.garage.repository.BayRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BayService {

    private final BayRepository repository;

    public BayService(BayRepository repository) {
        this.repository = repository;
    }

    public String save(Bay bay) {
        repository.save(bay);
        return "Bay saved successfully";
    }

    public List<Bay> getAll() {
        return repository.findAll();
    }

    public Optional<Bay> getById(UUID id) {
        return repository.findById(id);
    }

    public String update(UUID id, Bay updated) {
        Optional<Bay> result = repository.findById(id);

        if (result.isEmpty()) {
            return "The bay with that id does not exist";
        }

        Bay bay = result.get();
        bay.setBayNumber(updated.getBayNumber());
        bay.setName(updated.getName());
        repository.save(bay);

        return "Bay updated successfully";
    }

    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            return "The bay with that id does not exist";
        }

        repository.deleteById(id);
        return "Bay deleted successfully";
    }
}
