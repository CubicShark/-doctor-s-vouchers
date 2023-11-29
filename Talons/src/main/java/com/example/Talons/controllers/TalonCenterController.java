package com.example.Talons.controllers;

import com.example.Talons.services.TalonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/talonCenter")
public class TalonCenterController {

    private final TalonsService talonsService;


    @Autowired
    public TalonCenterController(TalonsService talonsService) {
        this.talonsService = talonsService;
    }

    @GetMapping()
    public String Home(){
        return "talonCenter/home";
    }

    @GetMapping("/admin")
    public String AdminPanel(Model model){
        model.addAttribute("notTakenTalons",talonsService.notTakenTalons(talonsService.findAll()));
        model.addAttribute("takenTalons",talonsService.takenTalons(talonsService.findAll()));
        System.out.println(talonsService.notTakenTalons(talonsService.findAll()));
        System.out.println("space");
        System.out.println(talonsService.takenTalons(talonsService.findAll()));
        return "talonCenter/admin";
    }
}
