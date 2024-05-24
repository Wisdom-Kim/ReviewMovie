package service;

import domain.User;
import dto.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import util.JpaUtil;

public class UserService {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
	
	public static boolean insertUser(UserDTO userDTO) {
    	boolean insertResult = true;
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
    	
		User user = null;
		
    	tx.begin();
		try {
			// 회원 등록
			user = UserDTO.toEntity(userDTO);
			em.persist(user);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			insertResult = false;
		} finally {
			em.close();
		}
				
		return insertResult;
    }

	public static UserDTO getUser(String accountId, String passwd) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
    	
		User user = null;
		
    	tx.begin();
		try {
			// 회원 조회
			String getUserJPQL = "SELECT u FROM User u WHERE u.user_account_id = :accountId AND u.user_passwd = :passwd";
			
			user = em.createQuery(getUserJPQL, User.class)
					.setParameter("accountId", accountId)
					.setParameter("passwd", passwd)
					.getSingleResult();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
				
		return User.toDTO(user);
	}
}