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

//import by.pinchuk.dao.company.ConsigneeDAO;
//import by.pinchuk.dao.company.ShipperDAO;
import by.pinchuk.dao.other.JDBCAbstractDAO;
import by.pinchuk.table.people.Logistician;

public class LogisticianDAO extends JDBCAbstractDAO implements ILogistician {
	
	private static Logger logger = LogManager.getLogger();
	public static final String SQL_SELECT_ALL_LOGISTICANS = "SELECT l.id, e.first_name, e.last_name, l.year_of_start_work, e.salary from logisticians l LEFT JOIN employee e ON l.Employee_id = e.id";
	public static final String SQL_SELECT_BY_ID = "SELECT l.id, e.first_name, e.last_name, l.year_of_start_work, e.salary from logisticians l LEFT JOIN employee e ON l.Employee_id = e.id WHERE l.id=?";
	public static final String SQL_DELETE_BY_ID = "DELETE FROM logisticians WHERE id = ?";
	public static final String SQL_CREATE_NEW_EMPLOYEE = "INSERT INTO employee (first_name, last_name, salary) VALUES (?,?,?)";	
	public static final String SQL_CREATE_NEW_LOGISTICAN = "INSERT INTO logisticians (year_of_start_work, Employee_id) VALUES (?, ?)";
	
	
	@Override
	public ArrayList<Logistician> allLogistician() {
		ArrayList<Logistician> logisticians = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_LOGISTICANS);
			rs = ps.executeQuery();
			while (rs.next()) {
				logisticians.add(createLogistician(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return logisticians;
	}
	
	@Override
	public Logistician selectById(long id) {
		Logistician logistician = new Logistician();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			logistician = createLogistician(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return logistician;
	}
	
	private Logistician createLogistician(ResultSet rs) {
		Logistician logistician = new Logistician();
		try {
//			CustomDAO cDAO = new CustomDAO();
//			ShipperDAO sDAO = new ShipperDAO();
//			ConsigneeDAO conDAO = new ConsigneeDAO();
//			logger.log(Level.INFO, "try to create logist");
			logistician.setId(rs.getInt("id"));
			logistician.setFirstName(rs.getString("first_name"));
			logistician.setLastName(rs.getString("last_name"));
			logistician.setYearOfStartWork(rs.getDate("year_of_start_work"));
			logistician.setSalary(rs.getInt("salary"));
//			logistician.setCustoms(cDAO.getCustomByLogistID(rs.getInt("id")));
//			logistician.setShipers(sDAO.getShipperByLogistID(rs.getInt("id")));
//			logistician.setConsignees(conDAO.getConsigneeByLogistID(rs.getInt("id")));
			
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		}
		return logistician;
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
	
	public void createNewLogistician(Logistician log) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_CREATE_NEW_EMPLOYEE);
			ps.setString(1, log.getFirstName());
			ps.setString(2, log.getLastName());
			ps.setInt(3, log.getSalary());
			ps.executeQuery();
			rs = ps.getGeneratedKeys();
			ps1 = conn.prepareStatement(SQL_CREATE_NEW_LOGISTICAN);
			ps1.setDate(1, (Date) log.getYearOfStartWork());
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
