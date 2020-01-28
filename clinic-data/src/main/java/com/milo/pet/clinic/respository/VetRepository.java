package com.milo.pet.clinic.respository;

import com.milo.pet.clinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
