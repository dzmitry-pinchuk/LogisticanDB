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
import by.pinchuk.table.company.Consignee;

public class ConsigneeDAO extends JDBCAbstractDAO implements IConsignee {

	private static Logger logger = LogManager.getLogger();
	private static final String SQL_SELECT_ALL_CONSIGNEE = "SELECT * FROM consignee";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM consignee WHERE id = ?";
//	private static final String SQL_SELECT_ALL_CONSIGNEE = "SELECT c.id, c.consignee_name, c.phone_number, co.country_name, ci.cities_name, s.street_name FROM consignee c LEFT JOIN adresses adr ON c.Adresses_id = adr.id LEFT JOIN countries co ON adr.countries_id = co.id LEFT JOIN cities ci ON adr.cities_id = ci.id LEFT JOIN streets s ON adr.streets_id = s.id";
//	private static final String SQL_SELECT_BY_ID = "SELECT c.id, c.consignee_name, c.phone_number, co.country_name, ci.cities_name, s.street_name FROM consignee c LEFT JOIN adresses adr ON c.Adresses_id = adr.id LEFT JOIN countries co ON adr.countries_id = co.id LEFT JOIN cities ci ON adr.cities_id = ci.id LEFT JOIN streets s ON adr.streets_id = s.id WHERE c.id=?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM consignee WHERE id = ?";
	private static final String SQL_CREATE_NEW_CONSIGNEE = "INSERT INTO consignee (consignee_name, phone_number) VALUES (?,?)";	
	private static final String SQL_SELECT_CONSIGNEE_BY_LOGIST_ID = "SELECT * FROM consignee_has_logisticians WHERE Logisticians_id = ?";
	
	private PreparedStatement ps = null;
	private Connection conn = null;
	private ResultSet rs = null;
	private ArrayList<Consignee> consignees = new ArrayList<>();
	
	private void setOptions(String SQL) {
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not executeQuery: " + e);
		}
	}
	
	public ArrayList<Consignee> allConsignee() {  
		setOptions(SQL_SELECT_ALL_CONSIGNEE);
		try {
			while (rs.next()) {
				consignees.add(createConsegnee(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return consignees;
	}

	public Consignee selectById(long id) {  
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Consignee consignee = new Consignee();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			consignee = createConsegnee(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return consignee;
	}
	
	private Consignee createConsegnee(ResultSet rs) {
		Consignee consignee = new Consignee();
		try {
//			AddressDAO adrDAO = new AddressDAO();
			consignee.setId(rs.getInt("id"));
			consignee.setConsigneeName(rs.getString("consignee_name"));
			consignee.setPhoneNumber(rs.getString("phone_number"));
//			consignee.setAdress(adrDAO.selectById(rs.getInt("Adresses_id")));
			Address address = new Address();
			address.setId(rs.getInt("Adresses_id"));
			consignee.setAddressConn(address);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from ResultSet: " + e);
		}
		return consignee;
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
			endOperation(ps, conn, rs);
		}	
	}

	@Override
	public void createNewConsignee(Consignee con) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_CREATE_NEW_CONSIGNEE);
			ps.setString(1, con.getConsigneeName());
			ps.setString(2, con.getPhoneNumber());
			ps.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not delete: " + e);
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "SQLException. Can not setAutoCommit(true) " + e);
			}
			endOperation(ps, conn, rs);
		}	
	}
	
	public ArrayList<Consignee> getConsigneeByLogistID(long id) {
		ArrayList<Consignee> consigneesByLogistID = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_CONSIGNEE_BY_LOGIST_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				consigneesByLogistID.add(selectById(rs.getInt("Consignee_id")));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return consigneesByLogistID;
	}

}
