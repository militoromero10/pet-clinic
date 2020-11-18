package com.milo.pet.clinic.service.jpa;

import com.milo.pet.clinic.model.Specialty;
import com.milo.pet.clinic.respository.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecialtyServiceJpaTest {

    @Mock
    SpecialtyRepository repository;

    @InjectMocks
    SpecialtyServiceJpa service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialties.add(new Specialty());
        when(repository.findAll()).thenReturn(specialties);
        Set<Specialty> specialtySet = service.findAll();
        assertThat(specialtySet).isNotNull();
        assertThat(specialtySet).isNotEmpty();
        assertThat(specialtySet).hasSize(1);
        verify(repository).findAll();
    }

    @Test
    void findById() {
        Specialty specialty = new Specialty();
        when(repository.findById(anyLong())).thenReturn(Optional.of(specialty));
        Specialty specialtyReturned =  service.findById(anyLong());
        assertThat(specialtyReturned).isNotNull();
        verify(repository).findById(anyLong());
    }

    @DisplayName("BDD Example")
    @Test
    void findByIdBddTest() {
        //given
        Specialty specialty = new Specialty();
        given(repository.findById(anyLong())).willReturn(Optional.of(specialty));

        //when
        Specialty specialtyReturned =  service.findById(anyLong());

        //then
        assertThat(specialtyReturned).isNotNull();
        then(repository).should().findById(anyLong());
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void save() {
        Specialty specialty = new Specialty();
        when(repository.save(any(Specialty.class))).thenReturn(specialty);
        Specialty returnedSpecialty = service.save(new Specialty());
        assertThat(returnedSpecialty).isNotNull();
        verify(repository).save(any(Specialty.class));
    }

    @Test
    void delete() {
        Specialty specialty = new Specialty();
        service.delete(specialty);
        verify(repository).delete(any(Specialty.class));
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(repository).deleteById(anyLong());

    }
}