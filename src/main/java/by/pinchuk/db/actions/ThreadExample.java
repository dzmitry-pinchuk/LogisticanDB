package by.pinchuk.db.actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.connection.pool.ConnectionPool;

public class ThreadExample extends Thread {
	private static Logger logger = LogManager.getLogger();
	private static final String QUERY = "SELECT * from Employee WHERE ID <3";
	private ConnectionPool cp;

	public ThreadExample(ConnectionPool cp) {
		this.cp = cp;
	}

	public void run() {
		Connection conn = null;
		try {
		logger.log(Level.INFO, "new Thread" + (this.getId() - 14) + " was created");
		conn = cp.getConnection();
		logger.log(Level.INFO, "Thread " + (this.getId() - 14) + " take connection");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(QUERY);
			while (rs.next()) {
				logger.log(Level.INFO, "Thread " + (this.getId() - 14) + ": " + rs.getString(1) + " " + rs.getString(2)
						+ " " + rs.getString(3));
			}
			System.out.println();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can't read");
		}
		 cp.returnConnection(conn);
		logger.log(Level.INFO, "Thread" + (this.getId() - 14) + " was closed");

	}
}
