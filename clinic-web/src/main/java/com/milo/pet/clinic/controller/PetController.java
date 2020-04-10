package com.milo.pet.clinic.controller;

import com.milo.pet.clinic.model.Owner;
import com.milo.pet.clinic.model.Pet;
import com.milo.pet.clinic.model.PetType;
import com.milo.pet.clinic.service.OwnerService;
import com.milo.pet.clinic.service.PetService;
import com.milo.pet.clinic.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable String ownerId){
        return ownerService.findById(Long.parseLong(ownerId));
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String createPetForm(Owner owner, Model model){
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String initCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model){
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(),true)!=null){
            result.rejectValue("name","duplicate","already exists");
        }
        owner.getPets().add(pet);
        if(result.hasErrors()){
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet) ;
            return "redirect:/owners/"+owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable String petId, Model model){
        model.addAttribute("pet",petService.findById(Long.parseLong(petId)));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model){
        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }

}
