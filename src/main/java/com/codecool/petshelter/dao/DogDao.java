package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Pet;

import java.util.List;

public interface DogDao {

    List<Pet> getAllDogs();
    Pet getDogById(int id);
    int addDog(Pet pet);
    boolean updateDog(Pet pet);
    boolean removeDog(int id);
}
