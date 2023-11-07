package com.kscm.hibernate;

import com.kscm.hibernate.entities.Employee;
import com.kscm.hibernate.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Test2PropsSetting {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        //create, none, update
        props.put("hibernate.hbm2ddl.auto", "create"); // never use this real world appln, only for practice

        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);
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
