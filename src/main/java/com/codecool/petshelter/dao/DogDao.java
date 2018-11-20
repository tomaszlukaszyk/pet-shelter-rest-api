package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Pet;

import java.util.List;

public interface DogDao {

    List<Pet> getAllDogs();
    Pet getDogById(long id);
    long addDog(Pet pet);
    boolean updateDog(Pet pet);
    boolean removeDog(long id);
}
