package com.codecool.petshelter;

import com.codecool.petshelter.dao.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class SimpleBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(PetDaoImpl.class).to(PetDao.class).in(Singleton.class);
        bind(CaretakerDaoImpl.class).to(CaretakerDao.class).in(Singleton.class);
        bind(AdoptionDaoImpl.class).to(AdoptionDao.class).in(Singleton.class);
        bind(PersistenceUtil.class).to(PersistenceUtil.class).in(Singleton.class);
    }
}
