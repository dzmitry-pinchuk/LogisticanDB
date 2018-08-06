package by.pinchuk.dao.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.dao.other.JDBCAbstractDAO;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.company.Shipper;

public class ShipperDAO extends JDBCAbstractDAO implements IShipper {
	
	private static final String SQL_SELECT_ALL_SHIPPER = "SELECT * FROM Shipper";
//	private static final String SQL_SELECT_ALL_SHIPPER = "SELECT s.shipper_name, s.phone_number, co.country_name, ci.cities_name, st.street_name FROM Shipper s \r\n"
//			+ "LEFT JOIN adresses adr ON s.Adresses_id = adr.id \r\n"
//			+ "LEFT JOIN countries co ON adr.countries_id = co.id \r\n"
//			+ "LEFT JOIN cities ci ON adr.cities_id = ci.id \r\n" + "LEFT JOIN streets st ON adr.streets_id = st.id";
//	private static final String SQL_SELECT_BY_ID = "SELECT s.shipper_name, s.phone_number, co.country_name, ci.cities_name, st.street_name FROM Shipper s \r\n"
//			+ "LEFT JOIN adresses adr ON s.Adresses_id = adr.id \r\n"
//			+ "LEFT JOIN countries co ON adr.countries_id = co.id \r\n"
//			+ "LEFT JOIN cities ci ON adr.cities_id = ci.id \r\n"
//			+ "LEFT JOIN streets st ON adr.streets_id = st.id WHERE s.id=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM Shipper WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM Shipper WHERE id = ?";
	private static final String SQL_CREATE_NEW_SHIPPER = "INSERT INTO Shipper (shipper_name, phone_number) VALUES (?,?)";
	private static final String SQL_SELECT_SHIPPER_BY_LOGIST_ID = "SELECT * FROM shipper_has_logisticians WHERE Logisticians_id = ?";
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public ArrayList<Shipper> allShipper() {
		ArrayList<Shipper> shippers = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_SHIPPER);
			rs = ps.executeQuery();
			while (rs.next()) {
				shippers.add(createShipper(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return shippers;
	}

	@Override
	public Shipper selectById(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Shipper shipper = new Shipper();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			shipper = createShipper(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return shipper;
	}

	private Shipper createShipper(ResultSet rs) {
		Shipper shipper = new Shipper();
		try {
//			AddressDAO adrDAO = new AddressDAO();
			shipper.setId(rs.getInt("id"));
			shipper.setShipperName(rs.getString("shipper_name"));
			shipper.setPhoneNumber(rs.getString("phone_number"));
//			shipper.setAdress(adrDAO.selectById(rs.getInt("Adresses_id")));
			Address address = new Address();
			address.setId(rs.getInt("Adresses_id"));
			shipper.setAddress(address);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not write in class: " + e);
		}
		return shipper;
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
	public void createNewShipper(Shipper sh) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_CREATE_NEW_SHIPPER);
			ps.setString(1, sh.getShipperName());
			ps.setString(2, sh.getPhoneNumber());
			ps.executeQuery();
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
	
	public ArrayList<Shipper> getShipperByLogistID(long id) {
		ArrayList<Shipper> shippers = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_SHIPPER_BY_LOGIST_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				shippers.add(selectById(rs.getInt("Shipper_id")));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
//			logger.log(Level.INFO, shippers);
		}
		return shippers;
	}

}
