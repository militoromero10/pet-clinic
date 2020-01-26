package com.milo.pet.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/owners")
public class OwnerController {

    @RequestMapping({"","/","/index","/index.html"})
    public String listOwner(){
        return "owners/index";
    }

}
