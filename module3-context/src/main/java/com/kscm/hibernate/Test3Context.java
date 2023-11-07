package com.kscm.hibernate;

import com.kscm.hibernate.entities.Employee;
import com.kscm.hibernate.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Test3Context {
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
            var e1 = new Employee();
            e1.setId(1);
            e1.setName("Annappa");
            e1.setAddress("Address");

            entityManager.persist(e1);
            //here, it just adds the entity e1 to the context, insert query wont get executed
            // insert query gets executed after the commit transaction

            Employee employee = entityManager.find(Employee.class, 1);
            // select query wont get executed as the entity is found in the context
            System.out.println(employee);

            entityManager.getTransaction().commit(); // insert query gets executed here(persist) call
        } finally {
            entityManager.close();
        }
    }
}
