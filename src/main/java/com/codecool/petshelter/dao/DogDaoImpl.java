package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Pet;
import com.codecool.petshelter.model.PetType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DogDaoImpl implements DogDao {

    List<Pet> dogs = new ArrayList<>();

    public DogDaoImpl() {
        dogs.add(new Pet("Rex", 3, "German Shepard",PetType.DOG));
        dogs.add(new Pet("Dot", 6, "Pomeranian", PetType.DOG));
    }


    @Override
    public List<Pet> getAllDogs() {
        dogs.forEach(System.out::println);
        return this.dogs;
    }

    @Override
    public Pet getDogById(int id) {
        Pet dog = null;
        try {
            dog = dogs.get(id);
        } catch (IndexOutOfBoundsException e) {

        }

        return dog;
    }

    @Override
    public int addDog(Pet pet) {
        if (dogs.add(pet)) {
            return dogs.indexOf(pet);
        }
        return -1;
    }

    @Override
    public boolean updateDog(Pet pet) {
        int index = dogs.indexOf(pet);
        if (index < 0) return false;
        dogs.set(index, pet);
        return true;
    }

    @Override
    public boolean removeDog(int id) {
        try {
            dogs.remove(id);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
}
