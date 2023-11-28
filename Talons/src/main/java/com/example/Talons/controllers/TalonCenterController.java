package com.example.Talons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/talonCenter")
public class TalonCenterController {
    @GetMapping()
    public String Home(){
        return "talonCenter/home";
    }

    @GetMapping("/adminPanel")
    public String AdminPanel(){
        return "talonCenter/adminPanel";
    }
}
