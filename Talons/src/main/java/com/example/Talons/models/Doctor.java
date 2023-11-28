package com.example.Talons.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Doctor")
public class Doctor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Количество символов м имени должно быть от 2 до 30")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Не может быть пустым")
    @Column(name = "speciality")
    private String speciality;

    @OneToMany(mappedBy = "doctor")
    private List<Talon> talons;

    public Doctor(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public Doctor() {
    }

    public List<Talon> getTalons() {
        return talons;
    }

    public void setTalons(List<Talon> talons) {
        this.talons = talons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
