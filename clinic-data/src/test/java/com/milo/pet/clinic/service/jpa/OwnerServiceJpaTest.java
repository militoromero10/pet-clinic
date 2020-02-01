package com.milo.pet.clinic.service.jpa;

import com.milo.pet.clinic.model.Owner;
import com.milo.pet.clinic.respository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJpaTest {

    public static final long ID = 1L;
    public static final String LASTNAME = "Romero";
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceJpa ownerServiceJpa;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(ID).lastName(LASTNAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(owner);
        Owner loaded = ownerServiceJpa.findByLastName(LASTNAME);
        assertEquals(LASTNAME, loaded.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(owner);
        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> returned = ownerServiceJpa.findAll();
        assertNotNull(returned);
        assertEquals(owners.size(),returned.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(owner));
        Owner returned = ownerServiceJpa.findById(ID);
        assertNotNull(returned);
        assertEquals(ID, returned.getId());
        verify(ownerRepository).findById(any());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());
        Owner returned = ownerServiceJpa.findById(ID);
        assertNull(returned);
        verify(ownerRepository).findById(any());
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner returned = ownerServiceJpa.save(any());
        assertNotNull(returned);
        assertEquals(ID, owner.getId());
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerServiceJpa.delete(any());
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerServiceJpa.deleteById(ID);
        verify(ownerRepository).deleteById(any());
    }
}