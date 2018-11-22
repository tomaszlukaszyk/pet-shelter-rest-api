package com.codecool.petshelter.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Caretaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "caretaker")
    private List<Pet> pets = new ArrayList<>();

    public Caretaker() {
    }

    public Caretaker(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
        pet.setCaretaker(this);
    }

    public void removePet(Pet pet) {
        this.pets.remove(pet);
        pet.setCaretaker(null);
    }
}
