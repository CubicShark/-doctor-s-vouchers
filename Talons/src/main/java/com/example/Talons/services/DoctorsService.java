package com.example.Talons.services;

import com.example.Talons.models.Doctor;
import com.example.Talons.models.Talon;
import com.example.Talons.repositories.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DoctorsService {

    private final DoctorsRepository doctorsRepository;

    @Autowired
    public DoctorsService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }

    public List<Doctor> findAll(){
        return doctorsRepository.findAll();
    }

    public Doctor findById(int id) {
        return doctorsRepository.findById(id);
    }
}
