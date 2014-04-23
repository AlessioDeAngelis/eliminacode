package it.alessio.eliminacode.common.persistance;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class SingletonEMF {
	private static EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("eliminacode");

private SingletonEMF(){}
    public static EntityManagerFactory get() {
    	if (emfInstance == null){
            emfInstance = Persistence.createEntityManagerFactory("eliminacode");
        }
        return emfInstance;
    }
}
