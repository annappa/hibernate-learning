package com.kscm.hibernate;

import com.kscm.hibernate.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // represents the context

        try{
            entityManager.getTransaction().begin();

            Product product = new Product();
            product.setId(11);
            product.setName("Beer");

            entityManager.persist(product); //add this to the context, Not an insert query
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
