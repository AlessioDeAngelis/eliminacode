package it.alessio.eliminacode.common.persistance;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.sql.Connection;

/**
 * It deals with the db and the CRUD operations
 * */
public class Repository {
	public Repository() {
	}

	public void persistService(Service service) {
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(service);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Service findServiceById(int id) {
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		int key = id + 1;
		Service service = entityManager.find(Service.class, key);
		entityManager.getTransaction().commit();
		entityManager.close();

		return service;
	}

	public void updateServiceCurrentNumber(Service service) {
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		// java.sql.Connection connection =
		// entityManager.unwrap(java.sql.Connection.class);
		// try {
		// connection.setTransactionIsolation(1);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		String update = "UPDATE Service s SET s.currentNumber = :currentNumber WHERE s.chiave = :chiave";

		Query query = entityManager.createQuery(update);
		query.setParameter("currentNumber", service.getCurrentNumber());
		query.setParameter("chiave", service.getChiave());
		query.executeUpdate();

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Service findOrCreateService(Service service) {
		Service returnedService = null;
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		returnedService = entityManager.find(Service.class, service.getChiave());
		if (returnedService == null) {
			entityManager.persist(service);
		}
		entityManager.getTransaction().commit();
		entityManager.close();

		return returnedService;
	}

	public Machine findOrCreateMachine(Machine machine) {
		Machine returnedService = null;
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		returnedService = entityManager.find(Machine.class, machine.getChiave());
		if (returnedService == null) {
			entityManager.persist(machine);
		}
		entityManager.getTransaction().commit();
		entityManager.close();

		return returnedService;
	}

	/**
	 * Done for performance reasons
	 * 
	 * */
	public int incrementNumberService(int id) {
		// TODO: do this in a way that is less coupled, maybe starting the
		// transaction in a different shared method
		EntityManager entityManager = SingletonEMF.get().createEntityManager();

		entityManager.getTransaction().begin();
		// java.sql.Connection connection =
		// entityManager.unwrap(java.sql.Connection.class);
		// try {
		// connection.setTransactionIsolation(1);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		int key = id + 1;
		Service service = entityManager.find(Service.class, key);
		String currentNumberAsString = service.getCurrentNumber();
		int nextNumber = 0;
		if (currentNumberAsString != null && !currentNumberAsString.equals("")) {
			nextNumber = (Integer.valueOf(currentNumberAsString) + 1) % 99;
		}
		String update = "UPDATE Service s SET s.currentNumber = :currentNumber WHERE s.chiave = :chiave";
		Query query = entityManager.createQuery(update);
		query.setParameter("currentNumber", "" + nextNumber);
		query.setParameter("chiave", service.getChiave());
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();

		return nextNumber;
	}

	public List<Service> findAllServices() {
		List<Service> services = new ArrayList<Service>();
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery("select l from Service l");
		services = q.getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();

		return services;
	}

	public List<Machine> findAllMachines() {
		List<Machine> machines = new ArrayList<Machine>();
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
		try {
			connection.setTransactionIsolation(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Query q = entityManager.createQuery("select m from Machine m");
		machines = q.getResultList();
		for (Machine machine : machines) {
			System.out.println(machine);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		return machines;
	}

	public void mergeMachine(Machine currentMachine) {
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(currentMachine);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void updateMachine(Machine machine, Service service) {
		EntityManager entityManager = SingletonEMF.get().createEntityManager();
		entityManager.getTransaction().begin();
		Machine machineUpdated = entityManager.find(Machine.class, machine.getChiave());
		Service serviceUpdated = entityManager.find(Service.class, service.getChiave());
//		machineUpdated.setServiceId(serviceUpdated);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
