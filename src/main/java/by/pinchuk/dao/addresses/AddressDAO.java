package by.pinchuk.dao.addresses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.dao.other.JDBCAbstractDAO;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.addresses.City;
import by.pinchuk.table.addresses.Country;
import by.pinchuk.table.addresses.Street;

public class AddressDAO extends JDBCAbstractDAO implements IAddress{
	
	private static Logger logger = LogManager.getLogger();
	private static final String SQL_SELECT_ALL_ADDRESSES = "SELECT adr.id, c.country_name, c.fare, c.road_quality, ct.cities_name, s.street_name FROM adresses adr LEFT JOIN countries c ON adr.countries_id = c.id LEFT JOIN cities ct ON adr.cities_id = ct.id LEFT JOIN streets s ON adr.streets_id = s.id;";
	private static final String SQL_SELECT_BY_ID = "SELECT adr.id, c.country_name, c.fare, c.road_quality, ct.cities_name, s.street_name FROM adresses adr LEFT JOIN countries c ON adr.countries_id = c.id LEFT JOIN cities ct ON adr.cities_id = ct.id LEFT JOIN streets s ON adr.streets_id = s.id WHERE adr.ID=?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM adresses WHERE id = ?";
	private static final String SQL_CREATE_NEW_ADDRESS = "INSERT INTO adresses (countries_id, cities_id, streets_id) VALUES (?, ?, ?)";
	private static final String SQL_CREATE_NEW_COUNTRY = "INSERT INTO countries (country_name) VALUES (?)";
	private static final String SQL_CREATE_NEW_CITY = "INSERT INTO cities(cities_name) VALUES (?)";
	private static final String SQL_CREATE_NEW_STREET = "INSERT INTO streets (street_name) VALUES (?)";
	
	@Override
	public ArrayList<Address> allAddresses() {
		ArrayList<Address> adresses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_ADDRESSES);
			rs = ps.executeQuery();
			while (rs.next()) {
				adresses.add(createAddress(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return adresses;
	}

	@Override
	public Address selectById(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Address address = new Address();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			address = createAddress(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return address;
	}


	private Address createAddress(ResultSet rs) {
		Address address = new Address();
		try {			
			address.setId(rs.getInt("id"));
			address.setCountry(new Country(rs.getString("country_name"), rs.getInt("fare"), rs.getString("road_quality")));
			address.setCity(new City(rs.getString("cities_name")));
			address.setStreet(new Street(rs.getString("street_name")));			
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from ResultSet: " + e);
		}
		return address;
		
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
	public void createAddress(Address adr) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps1 = conn.prepareStatement(SQL_CREATE_NEW_COUNTRY);
			ps1.setString(1, adr.getCountry().toString());
			ps1.executeQuery();
			rs1 = ps1.getGeneratedKeys();
			ps2 = conn.prepareStatement(SQL_CREATE_NEW_CITY);
			ps2.setString(1, adr.getCity().toString());
			ps2.executeQuery();
			rs2 = ps2.getGeneratedKeys();
			ps3 = conn.prepareStatement(SQL_CREATE_NEW_STREET);
			ps3.setString(1, adr.getStreet().toString());
			ps3.executeQuery();
			rs3 = ps3.getGeneratedKeys();
			ps = conn.prepareStatement(SQL_CREATE_NEW_ADDRESS);
			ps.setInt(1, rs1.getInt(1));
			ps.setInt(2, rs2.getInt(1));
			ps.setInt(3, rs3.getInt(1));
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
			endOperation(ps, conn);
			endOperation(ps1, rs1);
			endOperation(ps2, rs2);
			endOperation(ps3, rs3);
		}
	}

}
