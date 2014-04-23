package it.alessio.eliminacode.common.persistance;

import it.alessio.eliminacode.security.SecretKeyEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SecretKeyRepository {
	public void persistSecretKey(SecretKeyEntity key) {
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(key);
		entityManager.getTransaction().commit();
	}
	
	public SecretKeyEntity findSecretKey(){		
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("select l from SecretKeyEntity l");
		SecretKeyEntity key = (SecretKeyEntity) query.getResultList().get(0);
		entityManager.getTransaction().commit();
		return key;
	}
}
