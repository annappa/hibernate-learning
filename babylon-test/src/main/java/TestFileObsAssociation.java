import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;

public class TestFileObsAssociation {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CitizenPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        PermanentFile file1 = new PermanentFile(
                Long.parseLong("271341877549072462"),
                "/Users/annrames/Developer/tmp/mel/filestore/1879048400/1879048492/1879049107/c0a80006-896c-1ca9-8189-6c3ca9090000",
                "/Users/annrames/Developer/tmp/mel/filestore/1879048400/1879048492/1879049107/c0a80006-896c-1ca9-8189-6c3ca9090000",
                "sample 2 10 (5).pdf",
                "pdf",
                311389
                );

        FileObs fileObs1 = new FileObs();
        fileObs1.setExtFileId(1234);
        fileObs1.setObjectStorageStripeName("CEGBU-DEV-12345-S1");
        fileObs1.setBabylonFile(file1);

        em.persist(fileObs1);
        em.getTransaction().commit();
    }
}
