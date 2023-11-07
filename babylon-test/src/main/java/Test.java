import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;



public class Test {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CitizenPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        int pid = 13;
        String passportNum = "123456" + pid;
        Passport passport = new Passport();
        passport.setPnumber(passportNum);

        Citizen c = new Citizen();
        c.setCname("Annappa" + passportNum);

        // ASSOCIATE CITIZEN WITH PASSPORT
        passport.setCitizen(c);
        // ASSOCIATE PASSPORT WITH CITIZEN
        c.setPassport(passport);

        em.persist(passport);
        em.getTransaction().commit();

        em.getTransaction().begin();
        // CITIZEN CAN ACCESS PASSPORT
        Citizen cz = em.find(Citizen.class,pid);
        System.out.println("Citizen name is " + cz.getCname());
        System.out.println("Citizen passport number is " + cz.getPassport().getPnumber());

        // PASSPORT CAN ACCESS CITIZEN BECAUSE IT IS BIDIRECTIONAL
        Passport pt = em.find(Passport.class,cz.getPassport().getPid());
        System.out.println("Passport id is " + pt.getPid());
        System.out.println("Passport number is " + pt.getPnumber());
        System.out.println("Citizen name is " + pt.getCitizen().getCname());

        em.getTransaction().commit();
    }
}
