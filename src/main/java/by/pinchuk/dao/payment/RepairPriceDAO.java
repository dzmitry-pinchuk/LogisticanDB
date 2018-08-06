package by.pinchuk.dao.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.dao.other.JDBCAbstractDAO;
//import by.pinchuk.dao.people.DriverDAO;
//import by.pinchuk.dao.transport.ServiceDAO;
import by.pinchuk.table.payment.RepairPrice;
import by.pinchuk.table.people.Driver;
import by.pinchuk.table.transport.Service;

public class RepairPriceDAO extends JDBCAbstractDAO implements IRepairPrice {

	private static Logger logger = LogManager.getLogger();

	private static final String SQL_SELECT_ALL_REPAIR_PRICES = "SELECT * FROM repair_priñe";
//	private static final String SQL_SELECT_ALL_REPAIR_PRICES2 = "SELECT rp.id, s.service_name, s.phone_number,  t.brand, t.model, t.reg_number, rp.date, e.first_name, e.last_name, s.Adresses_id FROM repair_priñe rp\r\n"
//			+ "LEFT JOIN tracs t ON rp.tracs_id = t.id\r\n" + "LEFT JOIN drivers d ON rp.Drivers_id = d.id\r\n"
//			+ "LEFT JOIN employee e ON d.Employee_id = e.id\r\n" + "LEFT JOIN services s ON rp.services_id = s.id";
//	private static final String SQL_SELECT_BY_ID2 = "SELECT rp.id, s.service_name, t.brand, t.model, t.reg_number, rp.date, e.first_name, e.last_name, s.Adresses_id FROM repair_priñe rp\r\n"
//			+ "LEFT JOIN tracs t ON rp.tracs_id = t.id\r\n" + "LEFT JOIN drivers d ON rp.Drivers_id = d.id\r\n"
//			+ "LEFT JOIN employee e ON d.Employee_id = e.id\r\n"
//			+ "LEFT JOIN services s ON rp.services_id = s.id WHERE rp.id=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM repair_priñe WHERE id=?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM repair_priñe WHERE id = ?";
//	private static final String SQL_SELECT_BY_ID_TRACK = "SELECT rp.id, s.id, s.service_name, s.phone_number,  t.brand, t.model, t.reg_number, rp.date, e.first_name, e.last_name FROM repair_priñe rp\r\n"
//			+ "LEFT JOIN tracs t ON rp.tracs_id = t.id\r\n" + "LEFT JOIN drivers d ON rp.Drivers_id = d.id\r\n"
//			+ "LEFT JOIN employee e ON d.Employee_id = e.id\r\n" + "LEFT JOIN services s ON rp.services_id = s.id WHERE tracs_id=?";
	private static final String SQL_SELECT_BY_ID_TRACK2 = "SELECT * FROM repair_priñe WHERE tracs_id = ?";
	
	@Override
	public ArrayList<RepairPrice> allRepairPrice() {
		ArrayList<RepairPrice> repairPrice = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_REPAIR_PRICES);
			rs = ps.executeQuery();
			while (rs.next()) {
				repairPrice.add(createRepairPrice(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return repairPrice;
	}

	private RepairPrice createRepairPrice(ResultSet rs) {
		RepairPrice repairPrice = new RepairPrice();
		try {
//			ServiceDAO sDAO = new ServiceDAO();
//			DriverDAO dDAO = new DriverDAO();
			repairPrice.setId(rs.getInt("id"));
			repairPrice.setDate(rs.getDate("date"));
//			repairPrice.setService(sDAO.selectById(rs.getInt("services_id"))); 
//			repairPrice.setDriver(dDAO.selectById(rs.getInt("Drivers_id")));
			Service service = new Service();
			service.setId(rs.getInt("services_id"));
			repairPrice.setService(service);
			Driver driver = new Driver();
			driver.setId(rs.getInt("Drivers_id"));
			repairPrice.setDriver(driver);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		}
		return repairPrice;
	}

	@Override
	public RepairPrice selectById(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RepairPrice repairPrice = new RepairPrice();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			repairPrice = createRepairPrice(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return repairPrice;
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
	public void createNewRepairPrice(RepairPrice rp) {
		throw new UnsupportedOperationException("method not create");
	}
	
	
	public ArrayList<RepairPrice> allRepairPriceByTrackId(long idTrack) {
		ArrayList<RepairPrice> repairPrice = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID_TRACK2);
			ps.setLong(1, idTrack);
			rs = ps.executeQuery();
			while (rs.next()) {
				repairPrice.add(createRepairPrice(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return repairPrice;
	}

}
