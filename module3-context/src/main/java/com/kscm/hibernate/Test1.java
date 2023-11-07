package com.kscm.hibernate;

import com.kscm.hibernate.entities.Employee;
import com.kscm.hibernate.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Test1 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // represents the context

        try{
            entityManager.getTransaction().begin();

            Employee employee = entityManager.find(Employee.class, 1);
            System.out.println(employee);

            employee.setName("Anni");
            System.out.println(employee);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
