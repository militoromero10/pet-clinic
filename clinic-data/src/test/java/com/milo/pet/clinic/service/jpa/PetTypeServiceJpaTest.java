package com.milo.pet.clinic.service.jpa;

import com.milo.pet.clinic.model.PetType;
import com.milo.pet.clinic.respository.PetTypeRepository;
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
class PetTypeServiceJpaTest {

    @Mock
    PetTypeRepository repository;

    @InjectMocks
    PetTypeServiceJpa service;

    @Test
    void findAll() {
        Set<PetType> types = new HashSet<>();
        types.add(new PetType());
        when(repository.findAll()).thenReturn(types);
        Set<PetType> petTypes = service.findAll();
        assertThat(petTypes).isNotNull();
        assertThat(petTypes).isNotEmpty();
        assertThat(petTypes).hasSize(1);
        verify(repository).findAll();
    }

    @Test
    void findById() {
        PetType petType = new PetType();
        when(repository.findById(anyLong())).thenReturn(Optional.of(petType));
        PetType petTypeReturned = service.findById(anyLong());
        assertThat(petTypeReturned).isNotNull();
        verify(repository).findById(anyLong());
    }

    @Test
    void save() {
        PetType petType = new PetType();
        when(repository.save(any(PetType.class))).thenReturn(petType);
        PetType typeSaved = service.save(new PetType());
        assertThat(typeSaved).isNotNull();
        verify(repository).save(any(PetType.class));
    }

    @Test
    void delete() {
        PetType petType = new PetType();
        service.delete(petType);
        verify(repository).delete(any(PetType.class));
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(repository).deleteById(anyLong());
    }
}