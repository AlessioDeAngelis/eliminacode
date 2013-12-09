package it.alessio.eliminacode.common.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;


public class IdBroker {

	private static Logger logger = Logger
			.getLogger("it.alessio.eliminacode.common.persistance.IdBroker");
	private static boolean created = false;

	// valide per postgresql (anche queste stringhe potrebbero essere scritte
	// nel file di configurazione
	//private static final String ddlStatement = "CREATE SEQUENCE sequenza_id";
	private static final String query = "SELECT nextval('sequenza_id') AS id";;

	private IdBroker() {
	}

	/*private static void createSequence(Connection connection) {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(ddlStatement);
			if (statement.executeUpdate() == 1)
				created = true;
		} catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			try {
				throw new PersistenceException(e.getMessage());
			} catch (PersistenceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}*/

	public static Long getId(Connection connection) {
		long id = -1;
		if (!created) {
			//createSequence(connection);
			created = true;
		}
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			try {
				throw new PersistenceException(e.getMessage());
			} catch (PersistenceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return new Long(id);
	}
}