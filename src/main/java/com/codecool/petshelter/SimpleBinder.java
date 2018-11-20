package com.codecool.petshelter;

import com.codecool.petshelter.dao.DogDao;
import com.codecool.petshelter.dao.DogDaoImpl;
import com.codecool.petshelter.dao.PersistenceUtil;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class SimpleBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(DogDaoImpl.class).to(DogDao.class).in(Singleton.class);
        bind(PersistenceUtil.class).to(PersistenceUtil.class).in(Singleton.class);
    }
}
