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

import javax.validation.Valid;

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
        model.addAttribute("talons", doctorsService.findById(id).getTalons());
        model.addAttribute("idD",id);
        return "doctors/book";
    }

    //сделать чтобы выводило только свободные талоны

    @PostMapping("/book")
    public String bookP(@RequestParam(name = "idT") int idT,
                        @RequestParam(name = "idD") int idD,
                        Model model, @ModelAttribute("person") @Valid Person person,
                        BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            model.addAttribute("talons", doctorsService.findById(idD).getTalons());
            return "doctors/book";
        }
        peopleService.save(person);

        person.setTalon(talonsService.findById(idT));

        talonsService.findById(idT).setPerson(person);

        peopleService.save(person);

        talonsService.updateIsTaken(true,idT);
        System.out.println(person);



        return "redirect:/doctors/bookConfirmed";
    }

}
