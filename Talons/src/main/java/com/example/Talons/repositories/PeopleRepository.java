package com.example.Talons.repositories;

import com.example.Talons.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    List<Person> findAll();
    Person findById(int id);

    void deleteById(int id);

}
