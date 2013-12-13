package it.alessio.eliminacode.common.persistance;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			String query = "CREATE TABLE services( id INT PRIMARY KEY, chiave INT ,name VARCHAR(32),color VARCHAR(32),currentNumber VARCHAR(12))";
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

	public void createHistoryLinesTable() {
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "CREATE history_lines( id INT PRIMARY KEY, service_id INT, machine_id INT, date DATE)";
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
			statement.setInt(1, machine.getId());
			statement.setInt(2, machine.getChiave());
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
		Service service = null;
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "SELECT * FROM services s WHERE s.id = ?  ";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				service = new Service();
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
		Machine machine = null;
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "SELECT * FROM machines m WHERE m.id = ?  ";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				machine = new Machine();
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

	public List<Service> findAllServices() {
		List<Service> services = new ArrayList<Service>();
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "SELECT * FROM services";
			statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Service service = new Service();
				service.setChiave(result.getInt("chiave"));
				service.setId(result.getInt("id"));
				service.setName(result.getString("name"));
				service.setColor(result.getString("color"));
				service.setCurrentNumber(result.getString("currentNumber"));
				services.add(service);
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

		return services;
	}

	public List<Machine> findAllMachines() {
		List<Machine> machines = new ArrayList<Machine>();
		DataSource ds = DataSource.getInstance();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "SELECT * FROM machines ";
			statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Machine machine = new Machine();
				machine.setChiave(result.getInt("chiave"));
				machine.setId(result.getInt("id"));
				machine.setNumberYouAreServing(result.getInt("number"));
				machine.setServiceId(result.getInt("service_id"));
				machines.add(machine);
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
		return machines;
	}

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
			if (result == 1) {
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
			String query = "UPDATE machines SET number=?, service_id = ? WHERE id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, lastCalledNumberByMachine);
			statement.setInt(2, newServiceId);
			statement.setInt(3, machine.getId());
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
	
	private java.util.Date sql2util(java.sql.Date data) {
		return new java.util.Date(data.getTime());
	}

	private java.sql.Date util2sql(java.util.Date data) {
		return new java.sql.Date(data.getTime());
	}


}
