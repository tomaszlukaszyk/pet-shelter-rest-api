package com.codecool.petshelter.model;

public class Pet {

    private String name;
    private int age;
    private String breed;
    private PetType type;
    private boolean adopted = false;

    public Pet() {
    }

    public Pet(String name, int age, String breed, PetType type) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.type = type;
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
