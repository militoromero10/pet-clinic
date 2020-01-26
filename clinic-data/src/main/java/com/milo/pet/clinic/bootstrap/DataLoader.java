package com.milo.pet.clinic.bootstrap;

import com.milo.pet.clinic.model.Owner;
import com.milo.pet.clinic.model.Vet;
import com.milo.pet.clinic.service.OwnerService;
import com.milo.pet.clinic.service.VetService;
import com.milo.pet.clinic.service.map.OwnerServiceMap;
import com.milo.pet.clinic.service.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Camilo");
        owner1.setLastName("Romero");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Paula");
        owner2.setLastName("Romero");
        ownerService.save(owner2);
        System.out.println("Loaded owners ...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Jose");
        vet1.setLastName("Romero");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Sebastian");
        vet2.setLastName("Romero");
        vetService.save(vet2);

        System.out.println("Loaded vets ...");

    }
}
