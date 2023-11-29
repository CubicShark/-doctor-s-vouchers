package com.example.Talons.controllers;

import com.example.Talons.models.Person;
import com.example.Talons.models.Talon;
import com.example.Talons.services.DoctorsService;
import com.example.Talons.services.PeopleService;
import com.example.Talons.services.TalonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorsController {

    private final DoctorsService doctorsService;
    private final TalonsService talonsService;
    private final PeopleService peopleService;



    @Autowired
    public DoctorsController(DoctorsService doctorsService, TalonsService talonsService, PeopleService peopleService) {
        this.doctorsService = doctorsService;
        this.talonsService = talonsService;
        this.peopleService = peopleService;
    }

    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("doctors",doctorsService.findAll());
        return "doctors/show";
    }
//    @GetMapping("/{id}")
//    public String index(@PathVariable("id") int id, Model model) {
//        model.addAttribute("talons", doctorsService.findById(id).getTalons());
//        return "doctors/index";
//    }
//
    @GetMapping("/book")
    public String book(@RequestParam(name = "id") int id,Model model) {
        model.addAttribute("person" , new Person());
        model.addAttribute("talons",talonsService.notTakenTalons(doctorsService.findById(id).getTalons()));
        model.addAttribute("idD",id);
        return "doctors/book";
    }



    @PostMapping("/book")
    public String bookP(@RequestParam(name = "idT") int idT,
                        @RequestParam(name = "idD") int idD,
                        RedirectAttributes redirectAttributes,
                        Model model, @ModelAttribute("person") @Valid Person person,
                        BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            model.addAttribute("talons",talonsService.notTakenTalons( doctorsService.findById(idD).getTalons()));
            return "doctors/book";
        }

        peopleService.save(person);

        person.setTalon(talonsService.findById(idT));

        talonsService.findById(idT).setPerson(person);

        peopleService.save(person);

        talonsService.updateIsTaken(true,idT);

        redirectAttributes.addAttribute("idT", idT);
        return "redirect:/doctors/bookConfirmed";
    }

    @GetMapping("/bookConfirmed")
    public String checkinConfirmed(Model model,@RequestParam(name = "idT") int idT){
        model.addAttribute("doctor",talonsService.findById(idT).getDoctor());
        model.addAttribute("person", talonsService.findById(idT).getPerson());
        model.addAttribute("talon",talonsService.findById(idT));
        return "doctors/bookConfirmed";
    }


    @DeleteMapping("/bookConfirmed")
    public String cansel(@RequestParam(name = "personId") int id){


        //talonsService.findById(peopleService.findById(id).getTalon().getId()).setPerson(null);
        talonsService.updateIsTaken(false,peopleService.findById(id).getTalon().getId());
        peopleService.deleteById(id);

        return "redirect:/talonCenter";
    }

    @DeleteMapping("/bookConfirmedAT")
    public String canselAT(@RequestParam(name = "personId") int id){

        talonsService.deleteById(peopleService.findById(id).getTalon().getId());
        peopleService.deleteById(id);

        return "redirect:/talonCenter/admin";
    }

    @DeleteMapping("/bookConfirmedANT")
    public String canselANT(@RequestParam(name = "talonId") int id){

        talonsService.deleteById(id);

        return "redirect:/talonCenter/admin";
    }

}
