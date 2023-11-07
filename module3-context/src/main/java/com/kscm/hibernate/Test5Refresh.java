package com.kscm.hibernate;

import com.kscm.hibernate.entities.Employee;
import com.kscm.hibernate.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Test5Refresh {
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

            Employee employee = entityManager.find(Employee.class, 1);
            System.out.println("First load" + employee);
            employee.setName("Something");
            System.out.println("After update" + employee);

            entityManager.refresh(employee);
            // undo the changes done to the entity in the context and sets the data which is there in DB
            System.out.println("After refresh" + employee);
            entityManager.getTransaction().commit(); // insert query gets executed here(persist) call
        } finally {
            entityManager.close();
        }
    }
}
