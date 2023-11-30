package com.example.Talons.controllers;

import com.example.Talons.models.Doctor;
import com.example.Talons.models.Talon;
import com.example.Talons.services.DoctorsService;
import com.example.Talons.services.PeopleService;
import com.example.Talons.services.TalonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/talonCenter")
public class TalonCenterController {

    private final DoctorsService doctorsService;
    private final TalonsService talonsService;
    private final PeopleService peopleService;



    @Autowired
    public TalonCenterController(DoctorsService doctorsService, TalonsService talonsService, PeopleService peopleService) {
        this.doctorsService = doctorsService;
        this.talonsService = talonsService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String Home(){
        return "talonCenter/home";
    }

    @GetMapping("/admin")
    public String adminPanel(Model model){
        model.addAttribute("notTakenTalons",talonsService.notTakenTalons(talonsService.findAll()));
        model.addAttribute("takenTalons",talonsService.takenTalons(talonsService.findAll()));
        System.out.println(talonsService.notTakenTalons(talonsService.findAll()));
        System.out.println("space");
        System.out.println(talonsService.takenTalons(talonsService.findAll()));
        return "talonCenter/admin";
    }

    @GetMapping("/addTalon")
    public String addTalon(Model model){
        model.addAttribute("talon", new Talon());
        model.addAttribute("doctors",doctorsService.findAll());
        return "talonCenter/addTalon";
    }

    @PostMapping("/addTalon")
    public String addTalonP(@ModelAttribute("talon") @Valid Talon talon,BindingResult bindingResult,
                            @RequestParam(name = "idD") int idD,
                            Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("doctors",doctorsService.findAll());
            return "talonCenter/addTalon";
        }

        talon.setDoctor(doctorsService.findById(idD));
        talonsService.save(talon);
        return "redirect:/talonCenter/admin";
    }

    @GetMapping("/addDoctor")
    public String addDoctor(Model model){
        model.addAttribute("doctor", new Doctor());
        return "talonCenter/addDoctor";
    }

    @PostMapping("/addDoctor")
    public String addDoctorP(@ModelAttribute("doctor") @Valid Doctor doctor,
                             BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "talonCenter/addDoctor";
        }

        doctorsService.save(doctor);
        return "redirect:/talonCenter/admin";
    }

    @GetMapping("/deleteDoctor")
    public String deleteDoctor(Model model){
        model.addAttribute("doctors", doctorsService.findAll());
        return "talonCenter/deleteDoctor";
    }

    @DeleteMapping("/deleteDoctor")
    public String deleteDoctorP(Model model,@RequestParam(name = "id") int id){

        for(int i=0;i<doctorsService.findById(id).getTalons().size();i++){
            talonsService.deleteById(doctorsService.findById(id).getTalons().get(i).getId());
        }

        doctorsService.deleteById(id);

        model.addAttribute("doctors", doctorsService.findAll());
        return "talonCenter/deleteDoctor";
    }


}
