package com.milo.pet.clinic.service;

import com.milo.pet.clinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

}
