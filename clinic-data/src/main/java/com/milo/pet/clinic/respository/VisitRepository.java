package com.milo.pet.clinic.respository;

import com.milo.pet.clinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface  VisitRepository extends CrudRepository<Visit, Long> {
}
