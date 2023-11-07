package com.kscm.hibernate;

import com.kscm.hibernate.entities.Product;
import com.kscm.hibernate.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // represents the context

        try{
            entityManager.getTransaction().begin();

            Product product = new Product();
            product.setId(12);
            product.setName("Beer");

            entityManager.persist(product); //add this to the context, Not an insert query
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
