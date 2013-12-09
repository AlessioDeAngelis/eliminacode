package it.alessio.eliminacode.common.persistance;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * It deals with the db and the CRUD operations
 * */
public class JDBCRepository {
	public JDBCRepository() {
	}

	public void createServiceTable() {
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "CREATE TABLE services( id INT PRIMARY KEY, chiave INT ,name VARCHAR(32),color VARCHAR(32),currentNumber VARCHAR(12))"
					+ " VALUES (?,?,?,?,?)";
			statement = connection.prepareStatement(query);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createMachineTable() {
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "CREATE TABLE machines( id INT PRIMARY KEY, chiave INT ,number INT,service_id INT)";
			statement = connection.prepareStatement(query);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void persistService(Service service) {
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "INSERT INTO services(id,chiave,name,color,currentNumber)" + " VALUES (?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(2, service.getChiave());
			statement.setInt(1, service.getId());
			statement.setString(3, service.getName());
			statement.setString(4, service.getColor());
			statement.setString(5, service.getCurrentNumber());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void persistMachine(Machine machine) {
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "INSERT INTO machines(id,chiave,number,service_id)" + " VALUES (?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(2, machine.getChiave());
			statement.setInt(1, machine.getId());
			statement.setInt(3, machine.getNumberYouAreServing());
			statement.setInt(4, machine.getServiceId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Service findServiceById(int id) {
		Service service = new Service();
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "SELECT * DISTINCT FROM services s WHERE s.id = ?  ";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				service.setChiave(result.getInt("chiave"));
				service.setId(result.getInt("id"));
				service.setName(result.getString("name"));
				service.setColor(result.getString("color"));
				service.setCurrentNumber(result.getString("currentNumber"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return service;
	}

	public Machine findMachineById(int id) {
		Machine machine = new Machine();
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "SELECT * DISTINCT FROM machines m WHERE m.id = ?  ";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				machine.setChiave(result.getInt("chiave"));
				machine.setId(result.getInt("id"));
				machine.setNumberYouAreServing(result.getInt("number"));
				machine.setServiceId(result.getInt("service_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return machine;
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

	// public void updateMachine(Machine machine) {
	// DataSource ds = DataSource.getInstance();
	// Connection connection = ds.getConnection();
	// PreparedStatement statement = null;
	// try {
	// String query = "UPDATE machines SET number=? WHERE id = ? ";
	// statement = connection.prepareStatement(query);
	// statement.setInt(1, machine.getNumberYouAreServing());
	// statement.setInt(2, machine.getId());
	//
	// statement.executeUpdate();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	//
	// try {
	// if (statement != null)
	// statement.close();
	// if (connection != null)
	// connection.close();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }

	public Service updateService(Service service, String lastCalledNumber) {
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "UPDATE services SET currentNumber=? WHERE id = ? ";
			statement = connection.prepareStatement(query);
			statement.setString(1, lastCalledNumber);
			statement.setInt(2, service.getId());

			int result = statement.executeUpdate();
			if(result == 1){
				service.setCurrentNumber(lastCalledNumber);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return service;
	}

	/*
	 * includes the service
	 */
	public Machine updateMachine(Machine machine, int lastCalledNumberByMachine, int newServiceId) {
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "UPDATE machines SET number=?, service_id = ? WHERE id = ? and service_id=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, lastCalledNumberByMachine);
			statement.setInt(2, newServiceId);
			statement.setInt(3, machine.getId());
			statement.setInt(4, machine.getServiceId());
			statement.executeUpdate();

			machine.setNumberYouAreServing(lastCalledNumberByMachine);
			machine.setServiceId(newServiceId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return machine;
	}

}
