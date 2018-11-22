package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Caretaker;

import java.util.List;

public interface CaretakerDao {

    List<Caretaker> getAllCaretakers();
    Caretaker getCaretakerById(long id);
    long addCaretaker(Caretaker caretaker);
    boolean updateCaretaker(Caretaker caretaker);
    boolean removeCaretaker(long id);
}
