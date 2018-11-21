package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Pet;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public class DogDaoImpl implements DogDao {

    private PersistenceUtil persistenceUtil;

    @Inject
    public DogDaoImpl(PersistenceUtil persistenceUtil) {
        this.persistenceUtil = persistenceUtil;
    }

    @Override
    public List<Pet> getAllDogs() {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        List<Pet> dogs = em.createQuery("FROM Pet p WHERE p.type = 'DOG'").getResultList();

        transaction.commit();
        return dogs;
    }

    @Override
    public Pet getDogById(long id) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Pet dog = em.find(Pet.class, id);

        transaction.commit();
        return dog;
    }

    @Override
    public long addDog(Pet pet) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(pet);
            transaction.commit();
        } catch (EntityExistsException e) {
            return -1L;
        }
        return pet.getId();
    }

    @Override
    public boolean updateDog(Pet pet) {

        Pet dogInDb = getDogById(pet.getId());

        if (dogInDb == null) return false;

        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(pet);

        transaction.commit();
        return true;
    }

    @Override
    public boolean removeDog(long id) {

        Pet dog = getDogById(id);

        if (dog == null) return false;

        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(dog);

        transaction.commit();
        return true;
    }
}
