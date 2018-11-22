package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Adoption;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AdoptionDaoImpl implements AdoptionDao {

    private PersistenceUtil persistenceUtil;

    @Inject
    public AdoptionDaoImpl(PersistenceUtil persistenceUtil) {
        this.persistenceUtil = persistenceUtil;
    }

    @Override
    public List<Adoption> getAllAdoptions() {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        List<Adoption> adoptions = em.createQuery("FROM Adoption").getResultList();

        transaction.commit();
        return adoptions;
    }

    @Override
    public Adoption getAdoptionById(long id) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Adoption adoption = em.find(Adoption.class, id);

        transaction.commit();
        return adoption;
    }

    @Override
    public long addAdoption(Adoption adoption) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        long adoptionId = -1L;
        transaction.begin();

        try {
            em.persist(adoption);
            adoptionId = adoption.getId();
        } catch (EntityExistsException e) {
        }

        transaction.commit();
        return adoptionId;
    }

    @Override
    public boolean updateAdoption(Adoption adoption) {
        Adoption adoptionInDb = getAdoptionById(adoption.getId());

        if (adoptionInDb == null) return false;

        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(adoption);

        transaction.commit();
        return true;
    }

    @Override
    public boolean removeAdoption(long id) {
        Adoption adoptionInDb = getAdoptionById(id);

        if (adoptionInDb == null) return false;

        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(adoptionInDb);

        transaction.commit();
        return true;
    }
}
