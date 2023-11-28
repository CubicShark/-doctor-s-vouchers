package com.example.Talons.repositories;

import com.example.Talons.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DoctorsRepository extends JpaRepository<Doctor,Integer> {
    List<Doctor> findAll();
    Doctor findById(int id);
}
