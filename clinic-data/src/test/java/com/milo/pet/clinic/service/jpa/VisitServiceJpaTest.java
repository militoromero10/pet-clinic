package com.milo.pet.clinic.service.jpa;

import com.milo.pet.clinic.model.Visit;
import com.milo.pet.clinic.respository.VisitRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitServiceJpaTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitServiceJpa service;

    @DisplayName("Test find all visits")
    @Test
    void findAll() {
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        when(repository.findAll()).thenReturn(visits);
        Set<Visit> returnedList = service.findAll();
        assertThat(returnedList).isNotNull();
        assertThat(returnedList).isNotEmpty();
        assertThat(returnedList).hasSize(1);
        verify(repository).findAll();
    }

    @DisplayName("Test find single visit by id")
    @Test
    void findById() {
        Visit visit = new Visit();
        when(repository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit visitReturned = service.findById(anyLong());
        assertThat(visitReturned).isNotNull();
        verify(repository).findById(anyLong());
    }

    @DisplayName("Test save visit")
    @Test
    void save() {
        Visit visit = new Visit();
        when(repository.save(any(Visit.class))).thenReturn(visit);
        Visit returnedVisit = service.save(new Visit());
        assertThat(returnedVisit).isNotNull();
        verify(repository).save(any(Visit.class));
    }

    @DisplayName("Test delete visit object")
    @Test
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(repository).delete(any(Visit.class));
    }

    @DisplayName("Test delete visit by id")
    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(repository).deleteById(anyLong());
    }
}