package com.example.Talons.services;

import com.example.Talons.models.Person;
import com.example.Talons.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void deleteById(int id){
        peopleRepository.deleteById(id);
    }

    public Person findById(int id){
        return peopleRepository.findById(id);
    }
}
