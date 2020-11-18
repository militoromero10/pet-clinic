package com.milo.pet.clinic.service.jpa;

import com.milo.pet.clinic.model.Specialty;
import com.milo.pet.clinic.model.Vet;
import com.milo.pet.clinic.respository.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetServiceJpaTest {

    @Mock
    VetRepository repository;

    @InjectMocks
    VetServiceJpa service;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        Set<Vet> vets = new HashSet<>();
        vets.add(new Vet());
        when(repository.findAll()).thenReturn(vets);
        Set<Vet> vetSet = service.findAll();
        assertThat(vetSet).isNotNull();
        assertThat(vetSet).isNotEmpty();
        assertThat(vetSet).hasSize(1);
        verify(repository).findAll();
    }

    @Test
    void findById() {
        Vet vet = new Vet();
        when(repository.findById(anyLong())).thenReturn(Optional.of(vet));
        Vet vetReturned = service.findById(anyLong());
        assertThat(vetReturned).isNotNull();
        verify(repository).findById(anyLong());
    }

    @Test
    void save() {
        Vet vet = new Vet();
        when(repository.save(any(Vet.class))).thenReturn(vet);
        Vet returnedVet = service.save(new Vet());
        assertThat(returnedVet).isNotNull();
        verify(repository).save(any(Vet.class));
    }

    @Test
    void delete() {
        Vet vet = new Vet();
        service.delete(vet);
        verify(repository).delete(any(Vet.class));
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(repository).deleteById(anyLong());
    }
}