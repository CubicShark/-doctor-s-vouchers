package com.example.Talons.services;

import com.example.Talons.models.Talon;
import com.example.Talons.repositories.TalonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateIsTaken(boolean isFull,int id){
        String sql = "UPDATE Talon SET taken = ? where id = ? ";
        jdbcTemplate.update(sql,isFull,id);
    }
}
