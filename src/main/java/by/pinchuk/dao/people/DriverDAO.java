package by.pinchuk.dao.people;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.dao.other.JDBCAbstractDAO;
import by.pinchuk.table.people.Driver;

public class DriverDAO extends JDBCAbstractDAO implements IDriver {

	private static Logger logger = LogManager.getLogger();
	private static final String SQL_SELECT_ALL_DRIVER = "SELECT d.id, e.first_name, e.last_name, d.license_year, e.salary from drivers d LEFT JOIN employee e ON d.Employee_id = e.id";
	private static final String SQL_SELECT_BY_ID = "SELECT d.id, e.first_name, e.last_name, d.license_year, e.salary from drivers d LEFT JOIN employee e ON d.Employee_id = e.id WHERE d.ID=?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM drivers WHERE id = ?";
	private static final String SQL_CREATE_NEW_EMPLOYEE = "INSERT INTO employee (first_name, last_name, salary) VALUES (?,?,?)";	
	private static final String SQL_CREATE_NEW_DRIVER = "INSERT INTO drivers (license_year, Employee_id) VALUES (?, ?)";
	
	@Override
	public ArrayList<Driver> allDrivers() {
		ArrayList<Driver> drivers = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_DRIVER);
			rs = ps.executeQuery();
			while (rs.next()) {
				drivers.add(createDriver(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return drivers;
	}

	@Override
	public Driver selectById(long l) {
		Driver driver = new Driver();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, l);
			rs = ps.executeQuery();
			rs.next();
			driver = createDriver(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return driver;
	}

	private Driver createDriver(ResultSet rs) {
		Driver driver = new Driver();
		try {
			driver.setId(rs.getInt("id"));
			driver.setFirstName(rs.getString("first_name"));
			driver.setLastName(rs.getString("last_name"));
			driver.setLicenseYear(rs.getDate("license_year"));
			driver.setSalary(rs.getInt("salary"));
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can not read from field: " + e);
		}
		return driver;
	}

	@Override
	public void deleteById(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_DELETE_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not delete: " + e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, "SQLException. Can not rollback connection: " + e);
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "SQLException. Can not setAutoCommit(true) " + e);
			}
			endOperation(ps, conn, rs);
		}	
	}

	@Override
	public void createNewDriver(Driver dr) {

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_CREATE_NEW_EMPLOYEE);
			ps.setString(1, dr.getFirstName());
			ps.setString(2, dr.getLastName());
			ps.setInt(3, dr.getSalary());
			ps.executeQuery();
			rs = ps.getGeneratedKeys();
			ps1 = conn.prepareStatement(SQL_CREATE_NEW_DRIVER);
			ps1.setDate(1, (Date) dr.getLicenseYear());
			ps1.setInt(2, rs.getInt(1));
			ps1.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not delete: " + e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, "SQLException. Can not rollback connection: " + e);
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "SQLException. Can not setAutoCommit(true) " + e);
			}
			endOperation(ps, conn, rs);
			endOperation(ps1);
		}	
		
	}

}
