package com.milo.pet.clinic.service;

import com.milo.pet.clinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);
    Pet save(Pet owner);
    Set<Pet> findAll();

}
