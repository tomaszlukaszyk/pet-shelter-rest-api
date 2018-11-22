package com.codecool.petshelter.dao;

import com.codecool.petshelter.model.Caretaker;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CaretakerDaoImpl implements CaretakerDao {

    private PersistenceUtil persistenceUtil;

    @Inject
    public CaretakerDaoImpl(PersistenceUtil persistenceUtil) {
        this.persistenceUtil = persistenceUtil;
    }

    @Override
    public List<Caretaker> getAllCaretakers() {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        List<Caretaker> caretakers = em.createQuery("From Caretaker").getResultList();

        transaction.commit();
        return caretakers;
    }

    @Override
    public Caretaker getCaretakerById(long id) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Caretaker caretaker = em.find(Caretaker.class, id);

        transaction.commit();
        return caretaker;
    }

    @Override
    public long addCaretaker(Caretaker caretaker) {
        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        long caretakerId = -1L;
        transaction.begin();

        try {
            em.persist(caretaker);
            caretakerId = caretaker.getId();
        } catch (EntityExistsException e) {
        }

        transaction.commit();
        return caretakerId;
    }

    @Override
    public boolean updateCaretaker(Caretaker caretaker) {
        Caretaker caretakerInDb = getCaretakerById(caretaker.getId());

        if (caretakerInDb == null) return false;

        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(caretaker);

        transaction.commit();
        return true;
    }

    @Override
    public boolean removeCaretaker(long id) {
        Caretaker caretakerInDb = getCaretakerById(id);

        if (caretakerInDb == null) return false;

        EntityManager em = this.persistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(caretakerInDb);

        transaction.commit();
        return true;
    }
}
