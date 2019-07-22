package com.accenture.flowershop.be.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "com.accenture.flowershop");
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
