package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
	// persistence.xml 내의 persistence-unit tag name
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_config");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void closeEntityManager(EntityManager em) {
        if (em != null) {
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }

    // 싱글톤으로 변경
    private JpaUtil() {}
}