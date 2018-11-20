package com.codecool.petshelter.model;

import javax.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String breed;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @Column(nullable = false)
    private boolean adopted = false;

    public Pet() {
    }

    public Pet(String name, int age, String breed, PetType type) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.type = type;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    @Override
    public String toString() {
        return "Pet name: " + name
                + "\nAge: " + age
                + "\nBreed: " + breed
                +"\nType: " + type
                +"\nAdopted: " + adopted;
    }
}
