package by.pinchuk.connection.pool;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
	private static final ConnectionPool cp = new ConnectionPool();
	private static Logger logger = LogManager.getLogger();
	private static BlockingQueue<Connection> freeConns;
	private static String url;
	private static int connSize;
	private static String user;
	private static String password;
	private static ReentrantLock locker = new ReentrantLock();
	
	private ConnectionPool() {
		try {
//			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Properties props = new Properties();
			props.load(new FileReader("src\\main\\resources\\database.properties"));
			Class.forName(props.getProperty("driver"));
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			int connSize = Integer.parseInt(props.getProperty("connSize"));
			setCp(url, user, password, connSize);
		} catch (IOException e) {
			logger.log(Level.ERROR, "Can`t read file");
		} catch (ClassNotFoundException e) {
			logger.log(Level.ERROR, "ClassNotFoundException");
		}
	}
	
	public static ConnectionPool getCp() {	
		return cp;
	}
	
	public static ConnectionPool setCp(String url, String user, String password, int connSize) {	
		ConnectionPool.url = url;
		ConnectionPool.user = user;
		ConnectionPool.password = password;
		ConnectionPool.connSize = connSize;
		ConnectionPool.freeConns = new ArrayBlockingQueue<Connection>(connSize);
		return cp;
	}
	
	public int getFreeConnsSize() {
		return freeConns.size();
	}

	private void createConnection() {
		try {
			connSize--;
			freeConns.add(DriverManager.getConnection(url, user, password));
//			logger.log(Level.INFO, "new connection was created.");
		} catch (Exception e) {
			logger.log(Level.ERROR, "ERROR create connection");
		}
	}
	
	public Connection getConnection() {
		locker.lock();
		Connection conn = null;
		try {
			if (freeConns.size() == 0 && connSize > 0) {
				createConnection();
			}
				conn = freeConns.take();	
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "ERROR take connection", e);
		} finally {
			locker.unlock();
		}
		return conn;
	}
	
	public void returnConnection(Connection conn) {
			freeConns.add(conn);
//			logger.log(Level.INFO, "free connection was placed in the queue. Size: " + getFreeConnsSize());
	}

	public void killConnection(Connection conn) {
			DbUtils.closeQuietly(conn);
			connSize++;
			logger.log(Level.INFO, "connection was closed");
	}
}