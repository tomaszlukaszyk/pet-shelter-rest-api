package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Pet;
import com.codecool.petshelter.model.PetType;

import java.util.List;

public interface PetDao {

    List<Pet> getAllPetsByType(PetType type);
    Pet getPetById(long id, PetType type);
    long addPet(Pet pet);
    boolean updatePet(Pet pet);
    boolean removePet(long id, PetType type);
}
