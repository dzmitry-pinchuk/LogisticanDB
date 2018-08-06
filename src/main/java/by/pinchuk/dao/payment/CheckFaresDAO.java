package by.pinchuk.dao.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import by.pinchuk.dao.addresses.CountryDAO;
import by.pinchuk.dao.other.JDBCAbstractDAO;
//import by.pinchuk.dao.people.DriverDAO;
//import by.pinchuk.dao.transport.TrackDAO;
import by.pinchuk.table.addresses.Country;
import by.pinchuk.table.payment.CheckFare;
import by.pinchuk.table.people.Driver;

public class CheckFaresDAO extends JDBCAbstractDAO implements ICheckFares {
	private static Logger logger = LogManager.getLogger();

	private static final String SQL_SELECT_ALL_CHECKFARES = "SELECT * FROM check_fares";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM check_fares WHERE id = ?";
	private static final String SQL_SELECT_BY_TRACK_ID = "SELECT * FROM check_fares WHERE tracs_id = ?";
	
//	private static final String SQL_SELECT_ALL_CHECKFARES = "SELECT cf.id, t.brand, t.model, t.reg_number, co.country_name, cf.date, e.first_name, e.last_name FROM check_fares cf \r\n"
//			+ "LEFT JOIN tracs t ON cf.tracs_id = t.id\r\n" + "LEFT JOIN countries co ON cf.countries_id = co.id\r\n"
//			+ "LEFT JOIN drivers d ON cf.Drivers_id = d.id\r\n" + "LEFT JOIN employee e ON d.Employee_id = e.id";
//	private static final String SQL_SELECT_BY_ID = "SELECT cf.id, t.brand, t.model, t.reg_number, co.country_name, cf.date, e.first_name, e.last_name FROM check_fares cf \\r\\n\" + \r\n"
//			+ "			\"LEFT JOIN tracs t ON cf.tracs_id = t.id\\r\\n\" + \r\n"
//			+ "			\"LEFT JOIN countries co ON cf.countries_id = co.id\\r\\n\" + \r\n"
//			+ "			\"LEFT JOIN drivers d ON cf.Drivers_id = d.id\\r\\n\" + \r\n"
//			+ "			\"LEFT JOIN employee e ON d.Employee_id = e.id WHERE cf.id=?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM check_fares WHERE id = ?";

	@Override
	public ArrayList<CheckFare> allCheckFares() {
		ArrayList<CheckFare> checkFares = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_CHECKFARES);
			rs = ps.executeQuery();
			while (rs.next()) {
				checkFares.add(createCheckFare(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return checkFares;
	}

	private CheckFare createCheckFare(ResultSet rs) {
		CheckFare checkFare = new CheckFare();
		try {
//			TrackDAO tDAO = new TrackDAO();
//			DriverDAO dDAO = new DriverDAO();
//			CountryDAO cDAO = new CountryDAO();
			checkFare.setId(rs.getInt("id"));
			checkFare.setDate(rs.getDate("date"));
//			checkFare.setDriver(dDAO.selectById(rs.getInt("Drivers_id")));
//			checkFare.setTrack(tDAO.selectById(rs.getInt("tracs_id")));
//			checkFare.setCountry(cDAO.selectById(rs.getInt("countries_id")));
			Country country = new Country();
			country.setId(rs.getInt("countries_id"));
			checkFare.setCountry(country);
			Driver driver = new Driver();
			driver.setId(rs.getInt("Drivers_id"));
			checkFare.setDriver(driver);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		}
		return checkFare;
	}

	@Override
	public CheckFare selectById(long id) {
		CheckFare checkFare = new CheckFare();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			checkFare = createCheckFare(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return checkFare;
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
	public void createNewCheckFares(CheckFare cf) {
		throw new UnsupportedOperationException("method not create");
	}
	
	public ArrayList<CheckFare> allCheckFaresByTrackID(long idTrack){
		ArrayList<CheckFare> checkFaresByTrackID = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_TRACK_ID);
			ps.setLong(1, idTrack);
			rs = ps.executeQuery();
			while (rs.next()) {
				checkFaresByTrackID.add(createCheckFare(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return checkFaresByTrackID;
	}

}
