package by.pinchuk.db.actions;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.connection.pool.ConnectionPool;

public class ThreadRunner {

	public static void main(String[] args) {
		  Logger logger = LogManager.getLogger();
		
		logger.log(Level.INFO, "start thread");
		for (int i = 0; i <20; i++) {
			new ThreadExample(ConnectionPool.getCp()).start();
		}

	}

}
