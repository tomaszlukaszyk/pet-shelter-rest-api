package com.codecool.petshelter;

import com.codecool.petshelter.model.Pet;
import com.codecool.petshelter.model.PetType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PopulateDB {

    public static void main(String[] args) {
        Pet dog1 = new Pet("Rex", 3, "German Shepard", PetType.DOG);
        Pet dog2 = new Pet("Dot", 6, "Pomeranian", PetType.DOG);
        Pet dog3 = new Pet("Brian", 8, "Unknown", PetType.DOG);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pet-shelter-jpa");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(dog1);
        em.persist(dog2);
//        em.persist(dog3);
        transaction.commit();
        em.close();
        entityManagerFactory.close();
    }
}
