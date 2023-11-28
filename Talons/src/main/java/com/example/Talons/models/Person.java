package com.example.Talons.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Количество символов м имени должно быть от 2 до 30")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Номер телефона не должен быть пустым")
    //@Pattern(regexp="^\\+[0-9]{10}$", message="Некорректный формат номера телефона")
    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "person")
    private Talon talon;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Person(String name, String phone, Talon talon) {
        this.name = name;
        this.phone = phone;
        this.talon = talon;
    }

    public Person() {
    }

    public Talon getTalon() {
        return talon;
    }

    public void setTalon(Talon talon) {
        this.talon = talon;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
