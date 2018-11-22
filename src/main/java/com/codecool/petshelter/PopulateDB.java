package com.codecool.petshelter;

import com.codecool.petshelter.model.Caretaker;
import com.codecool.petshelter.model.Pet;
import com.codecool.petshelter.model.PetType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class PopulateDB {

    public static void main(String[] args) {

        List<Pet> pets = Arrays.asList(
                new Pet("Rex", 3, "German Shepherd", PetType.DOG),
                new Pet("Dot", 6, "Pomeranian", PetType.DOG),
                new Pet("Brian", 8, "Beagle", PetType.DOG),
                new Pet("Alfie", 4, "Bulldog", PetType.DOG),
                new Pet("Milo", 2, "Chihuahua", PetType.DOG),
                new Pet("Hunter", 9, "Collie", PetType.DOG),
                new Pet("Toby", 2, "Siamese", PetType.CAT),
                new Pet("Fluffy", 6, "Persian", PetType.CAT),
                new Pet("Muffin", 5, "Munchkin", PetType.CAT),
                new Pet("Cleo", 2, "Aegean", PetType.CAT),
                new Pet("Ginger", 3, "Bengal", PetType.CAT),
                new Pet("Oreo", 4, "Chartreux", PetType.CAT)

        );

        List<Caretaker> caretakers = Arrays.asList(
                new Caretaker("Amy", "576-834-331"),
                new Caretaker("Bob", "883-835-483"),
                new Caretaker("Sharon", "555-811-112")
        );

        int counter = 0;
        for (Caretaker caretaker: caretakers) {
            for (int i=0; i<4; i++) {
                caretaker.addPet(pets.get(counter++));
            }
        }


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pet-shelter-jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (Caretaker caretaker: caretakers) {
            em.persist(caretaker);
        }
        for (Pet pet: pets) {
            em.persist(pet);
        }
        transaction.commit();
        em.close();
        entityManagerFactory.close();
    }
}
