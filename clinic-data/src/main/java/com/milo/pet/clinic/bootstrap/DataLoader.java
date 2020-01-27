package com.milo.pet.clinic.bootstrap;

import com.milo.pet.clinic.model.*;
import com.milo.pet.clinic.service.OwnerService;
import com.milo.pet.clinic.service.PetTypeService;
import com.milo.pet.clinic.service.SpecialtyService;
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
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Perro");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Gato");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiologia");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Cirugia");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Odontologia");
        Specialty savedDentisty = specialtyService.save(dentistry);

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
        vet1.getSpecialties().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sebastian");
        vet2.setLastName("Romero");
        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);
        System.out.println("Loaded vets ...");
    }
}
