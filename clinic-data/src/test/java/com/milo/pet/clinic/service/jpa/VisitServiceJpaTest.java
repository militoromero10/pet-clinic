package com.milo.pet.clinic.service.jpa;

import com.milo.pet.clinic.model.Visit;
import com.milo.pet.clinic.respository.VisitRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VisitServiceJpaTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitServiceJpa service;


    @DisplayName("Test find all visits")
    @Test
    void findAll() {
        //given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(repository.findAll()).willReturn(visits);

        //when
        Set<Visit> foundVisits = service.findAll();

        //then
        then(repository).should().findAll();
        assertThat(foundVisits).hasSize(1);
    }

    @DisplayName("Test find single visit by id")
    @Test
    void findById() {
        //given
        Visit visit = new Visit();
        given(repository.findById(anyLong())).willReturn(Optional.of(visit));

        //when
        Visit foundVisit = service.findById(1L);

        //then
        then(repository).should().findById(anyLong());
        assertThat(foundVisit).isNotNull();
    }

    @DisplayName("Test save visit")
    @Test
    void save() {
        //given
        Visit visit = new Visit();
        given(repository.save(any(Visit.class))).willReturn(visit);

        //when
        Visit savedVisit = service.save(new Visit());

        //then
        then(repository).should().save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @DisplayName("Test delete visit object")
    @Test
    void delete() {
        //given
        Visit visit = new Visit();

        //when
        service.delete(visit);

        //then
        then(repository).should().delete(any(Visit.class));
    }

    @DisplayName("Test delete visit by id")
    @Test
    void deleteById() {
        //when
        service.deleteById(1L);

        //then
        then(repository).should().deleteById(anyLong());
    }
}