package com.example.Talons.services;

import com.example.Talons.models.Doctor;
import com.example.Talons.models.Talon;
import com.example.Talons.repositories.TalonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TalonsService {

    private final TalonsRepository talonsRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TalonsService(TalonsRepository talonsRepository, JdbcTemplate jdbcTemplate) {
        this.talonsRepository = talonsRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Talon findById(int id){
        return talonsRepository.findById(id);
    }

    public List<Talon> findAll(){
        return talonsRepository.findAll();
    }

    public List<Talon> notTakenTalons(List<Talon> talons){
        talons.removeIf(Talon::isTaken);
        return talons;
    }
    public List<Talon> takenTalons(List<Talon> talons){
        List<Talon> takenTalons = new ArrayList<>() {
        };
        for (Talon talon : talons) {
            if (talon.isTaken()) {
                takenTalons.add(talon);
            }
        }
        return takenTalons;
    }

    @Transactional
    public void save(Talon talon){
        talonsRepository.save(talon);
    }

    @Transactional
    public void updateIsTaken(boolean isFull,int id){
        String sql = "UPDATE Talon SET taken = ? where id = ? ";
        jdbcTemplate.update(sql,isFull,id);
    }

    @Transactional
    public void deleteById(int id){
        talonsRepository.deleteById(id);
    }
}
