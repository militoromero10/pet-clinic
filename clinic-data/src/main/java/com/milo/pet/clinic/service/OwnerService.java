package com.milo.pet.clinic.service;

import com.milo.pet.clinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
