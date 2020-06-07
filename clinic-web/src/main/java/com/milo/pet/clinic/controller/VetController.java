package com.milo.pet.clinic.controller;

import com.milo.pet.clinic.model.Vet;
import com.milo.pet.clinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping({"/vets","/vets.html"})
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"","/", "/index","/index.html"})
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson(){
        return vetService.findAll();
    }
}
