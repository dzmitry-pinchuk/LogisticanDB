package by.pinchuk.dao.other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.connection.pool.ConnectionPool;

public class JDBCAbstractDAO {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool cp = ConnectionPool.getCp();

	public Connection getConnection() {
		return cp.getConnection();
	}

	public void closeResultSet(ResultSet rs) {
		try {
			DbUtils.close(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ResultSet close error", e);
		}
	}

	public void endOperation(PreparedStatement ps, Connection conn, ResultSet rs) {
		try {
			DbUtils.close(ps);
			cp.returnConnection(conn);
			DbUtils.close(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ResultSet close error", e);
		}
	}

	public void endOperation(PreparedStatement ps) {
		try {
			DbUtils.close(ps);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ResultSet close error", e);
		}
	}

	public void endOperation(PreparedStatement ps, ResultSet rs) {
		try {
			DbUtils.close(ps);
			DbUtils.close(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ResultSet close error", e);
		}
	}

	public void endOperation(PreparedStatement ps, Connection conn) {
		try {
			DbUtils.close(ps);
			cp.returnConnection(conn);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ResultSet close error", e);
		}
	}

}
