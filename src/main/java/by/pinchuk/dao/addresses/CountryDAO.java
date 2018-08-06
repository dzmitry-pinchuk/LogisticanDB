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
import by.pinchuk.table.addresses.Country;

public class CountryDAO extends JDBCAbstractDAO implements ICountry {

	private static Logger logger = LogManager.getLogger();
	
	private static final String SQL_SELECT_ALL_COUNTRY = "SELECT * FROM countries";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM countries WHERE id = ?";
	
	@Override
	public ArrayList<Country> allCountry() {
		ArrayList<Country> countries = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_COUNTRY);
			rs = ps.executeQuery();
			while (rs.next()) {
				countries.add(createCountry(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return countries;
	}

	private Country createCountry(ResultSet rs) {
		Country country = new Country();
		try {
			country.setId(rs.getInt("id"));
			country.setCountryName(rs.getString("country_name"));
			country.setFare(rs.getInt("fare"));
			country.setRoadQuality(rs.getString("road_quality"));
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		}
		
		return country;
	}

	@Override
	public Country selectById(long l) {
		Country country = new Country();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, l);
			rs = ps.executeQuery();
			rs.next();
			country = createCountry(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return country;
	}

	@Override
	public void deleteById(long id) {
		throw new UnsupportedOperationException("method not create");
	}

	@Override
	public void createAddress(Country country) {
		throw new UnsupportedOperationException("method not create");
	}

}
