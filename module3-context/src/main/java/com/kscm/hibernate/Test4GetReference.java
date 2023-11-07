package com.kscm.hibernate;

import com.kscm.hibernate.entities.Employee;
import com.kscm.hibernate.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Test4GetReference {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        //create, none, update
        props.put("hibernate.hbm2ddl.auto", "update"); // never use this real world appln, only for practice

        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // represents the context

        try{
            entityManager.getTransaction().begin();

            //Employee employee = entityManager.find(Employee.class, 1);
            //This triggers the select query

            Employee employee = entityManager.getReference(Employee.class, 1);
            //This won't trigger the select query immediately
            //System.out.println("Employee's name is: " + employee.getName());
            // Query gets triggered when we try to access date of the reference

            entityManager.getTransaction().commit(); // insert query gets executed here(persist) call
        } finally {
            entityManager.close();
        }
    }
}
