package com.example.Talons.repositories;

import com.example.Talons.models.Talon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TalonsRepository extends JpaRepository<Talon,Integer> {
    Talon findById(int id);
    List<Talon> findAll();

    void deleteById(int id);

}
