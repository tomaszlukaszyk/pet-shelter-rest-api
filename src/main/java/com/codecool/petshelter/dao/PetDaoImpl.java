package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Pet;
import com.codecool.petshelter.model.PetType;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public class PetDaoImpl implements PetDao {

    private PersistenceUtil persistenceUtil;

    @Inject
    public PetDaoImpl(PersistenceUtil persistenceUtil) {
        this.persistenceUtil = persistenceUtil;
    }

    @Override
    public List<Pet> getAllPetsByType(PetType type) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        List<Pet> dogs = em.createQuery("FROM Pet p FULL JOIN p.adoption a WHERE a.id = null AND p.type = :type ORDER BY p.id")
                .setParameter("type", type).getResultList();

        transaction.commit();
        return dogs;
    }

    @Override
    public Pet getPetById(long id, PetType type) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Pet pet = em.find(Pet.class, id);

        transaction.commit();

        if (pet != null && pet.getType() == type) return null;

        return pet;
    }

    @Override
    public long addPet(Pet pet) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        long petId = -1L;
        transaction.begin();

        try {
            em.persist(pet);
            petId = pet.getId();
        } catch (EntityExistsException e) {
        }

        transaction.commit();
        return petId;
    }

    @Override
    public boolean updatePet(Pet pet) {

        Pet dogInDb = getPetById(pet.getId(), PetType.DOG);

        if (dogInDb == null) return false;

        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(pet);

        transaction.commit();
        return true;
    }

    @Override
    public boolean removePet(long id, PetType type) {

        Pet dog = getPetById(id, PetType.DOG);

        if (dog == null) return false;

        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(dog);

        transaction.commit();
        return true;
    }
}
