package com.milo.pet.clinic.service.jpa;

import com.milo.pet.clinic.model.Specialty;
import com.milo.pet.clinic.respository.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialtyServiceJpaTest {

    /**
     * Behavior Development Driven Example
     */

    @Mock
    SpecialtyRepository repository;

    @InjectMocks
    SpecialtyServiceJpa service;

    @Test
    void testDeleteByObject() {
        //given
        Specialty speciality = new Specialty();

        //when
        service.delete(speciality);

        //then
        then(repository).should().delete(any(Specialty.class));
    }

    @Test
    void findByIdTest() {
        //given
        Specialty speciality = new Specialty();
        given(repository.findById(1L)).willReturn(Optional.of(speciality));

        //when
        Specialty foundSpecialty = service.findById(1L);

        //then
        assertThat(foundSpecialty).isNotNull();
        then(repository).should().findById(anyLong());
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        //given - none

        //when
        service.deleteById(1l);
        service.deleteById(1l);

        //then
        then(repository).should(times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        //given

        //when
        service.deleteById(1l);
        service.deleteById(1l);

        //then
        then(repository).should(atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        //when
        service.deleteById(1l);
        service.deleteById(1l);

        //then
        then(repository).should(atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {

        //when
        service.deleteById(1l);
        service.deleteById(1l);

        //then
        then(repository).should(atLeastOnce()).deleteById(1L);
        then(repository).should(never()).deleteById(5L);

    }

    @Test
    void testDelete() {
        //when
        service.delete(new Specialty());

        //then
        then(repository).should().delete(any());
    }
}