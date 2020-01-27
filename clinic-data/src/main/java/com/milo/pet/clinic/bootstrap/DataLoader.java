package com.milo.pet.clinic.bootstrap;

import com.milo.pet.clinic.model.Owner;
import com.milo.pet.clinic.model.Pet;
import com.milo.pet.clinic.model.PetType;
import com.milo.pet.clinic.model.Vet;
import com.milo.pet.clinic.service.OwnerService;
import com.milo.pet.clinic.service.PetTypeService;
import com.milo.pet.clinic.service.VetService;
import com.milo.pet.clinic.service.map.OwnerServiceMap;
import com.milo.pet.clinic.service.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Perro");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Gato");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Camilo");
        owner1.setLastName("Romero");
        owner1.setAddress("123 Calle");
        owner1.setCity("Bogota");
        owner1.setTelephone("1231231234");

        Pet adhara = new Pet();
        adhara.setPetType(savedDogPetType);
        adhara.setOwner(owner1);
        adhara.setBirthDate(LocalDate.now());
        adhara.setName("Adhara");
        owner1.getPets().add(adhara);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Paula");
        owner2.setLastName("Romero");
        owner2.setAddress("123 Carrera");
        owner2.setCity("Bogota");
        owner2.setTelephone("1231231234");

        Pet agustin = new Pet();
        agustin.setName("Lucky");
        agustin.setOwner(owner2);
        agustin.setBirthDate(LocalDate.now());
        agustin.setPetType(savedCatPetType);
        owner2.getPets().add(agustin);

        ownerService.save(owner2);
        System.out.println("Loaded owners ...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jose");
        vet1.setLastName("Romero");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sebastian");
        vet2.setLastName("Romero");
        vetService.save(vet2);

        System.out.println("Loaded vets ...");

    }
}
