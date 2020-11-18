package com.milo.pet.clinic.controller;

import com.milo.pet.clinic.model.Owner;
import com.milo.pet.clinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        //buenas practicas para evitar manipular campos en formulario
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){

        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        List<Owner> results = new ArrayList<>(ownerService.findAllByLastNameLike(owner.getLastName()));

        if(results.isEmpty()){
            result.rejectValue("lastName","notFound", "not found");
            return "owners/findOwners";
        } else if(results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/"+owner.getId();
        } else {
          model.addAttribute("selections", results);
          return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String createOwnerForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String createOwner(@Valid Owner owner, BindingResult result) {
        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        } else {
            Owner ownerSaved = ownerService.save(owner);
            return "redirect:/owners/"+ownerSaved.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String updateOwnerForm(@PathVariable String ownerId,  Model model) {
        model.addAttribute("owner", ownerService.findById(Long.parseLong(ownerId)));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String updateOwner(@Valid Owner owner, BindingResult result, @PathVariable String ownerId) {

        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        } else {
            owner.setId(Long.parseLong(ownerId));
            Owner owner1 = ownerService.save(owner);
            return "redirect:/owners/"+owner1.getId();
        }

    }

}
