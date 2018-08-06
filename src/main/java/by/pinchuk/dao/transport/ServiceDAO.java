package by.pinchuk.dao.transport;

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
import by.pinchuk.table.transport.Service;

public class ServiceDAO extends JDBCAbstractDAO implements IService{
	
	private static Logger logger = LogManager.getLogger();
	
	private static final String SQL_SELECT_ALL_SERVICES = "SELECT * FROM services";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM services WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM services WHERE id = ?";
	private static final String SQL_CREATE_NEW_SERVICE = "INSERT INTO services (service_name, phone_number) VALUES (?,?)";	


	@Override
	public ArrayList<Service> allServices() {
		ArrayList<Service> services = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_SERVICES);
			rs = ps.executeQuery();
			while (rs.next()) {
				services.add(createService(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return services;
	}
	
	private Service createService(ResultSet rs) {
		Service service = new Service();
		try {
//			AddressDAO adrDAO = new AddressDAO();
			service.setId(rs.getInt("id"));
			service.setServiceName(rs.getString("service_name"));
			service.setPhoneNumber(rs.getString("phone_number"));
//			service.setAddress(adrDAO.selectById(rs.getInt("Adresses_id")));
			Address address = new Address();
			address.setId(rs.getInt("Adresses_id"));
			service.setAddress(address);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		}
		return service;
		}

	@Override
	public Service selectById(long id) {
		Service service = new Service();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			service = createService(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return service;
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
	public void createNewService(Service sr) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_CREATE_NEW_SERVICE);
			ps.setString(1, sr.getServiceName());
			ps.setString(2, sr.getPhoneNumber());
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
}
