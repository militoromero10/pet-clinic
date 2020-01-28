package com.milo.pet.clinic.respository;

import com.milo.pet.clinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
