package com.milo.pet.clinic.service.jpa;

import com.milo.pet.clinic.model.Pet;
import com.milo.pet.clinic.respository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceJpaTest {

    @Mock
    PetRepository repository;

    @InjectMocks
    PetServiceJpa service;

    @Test
    void findAll() {
        Set<Pet> pets = new HashSet<>();
        pets.add(new Pet());
        when(repository.findAll()).thenReturn(pets);
        Set<Pet> petSet = service.findAll();

        assertThat(petSet).isNotNull();
        assertThat(petSet).isNotEmpty();
        assertThat(petSet).hasSize(1);

        verify(repository).findAll();
    }

    @Test
    void findById() {
        Pet pet = new Pet();
        when(repository.findById(anyLong())).thenReturn(Optional.of(pet));
        Pet petReturned = service.findById(anyLong());

        assertThat(petReturned).isNotNull();
        verify(repository).findById(anyLong());
    }

    @Test
    void save() {
        Pet pet = new Pet();
        when(repository.save(any(Pet.class))).thenReturn(pet);
        Pet petSaved = service.save(new Pet());

        assertThat(petSaved).isNotNull();
        verify(repository).save(any(Pet.class));
    }

    @Test
    void delete() {
        Pet pet = new Pet();
        service.delete(pet);
        verify(repository).delete(any(Pet.class));
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(repository).deleteById(anyLong());
    }
}