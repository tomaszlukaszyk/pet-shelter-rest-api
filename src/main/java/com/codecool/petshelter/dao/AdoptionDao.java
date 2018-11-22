package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Adoption;

import java.util.List;

public interface AdoptionDao {

    List<Adoption> getAllAdoptions();
    Adoption getAdoptionById(long id);
    long addAdoption(Adoption adoption);
    boolean updateAdoption(Adoption adoption);
    boolean removeAdoption(long id);
}
