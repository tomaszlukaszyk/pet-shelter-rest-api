package com.codecool.petshelter.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public PersistenceUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pet-shelter-jpa");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
