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
//import by.pinchuk.dao.people.DriverDAO;
//import by.pinchuk.dao.people.LogisticianDAO;
//import by.pinchuk.dao.transport.TrackDAO;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.company.Company;

public class CompanyDAO extends JDBCAbstractDAO implements ICompany{
	
	private static Logger logger = LogManager.getLogger();
	
	private static final String SQL_SELECT_ALL_COMPANY = "SELECT * FROM ñompanies";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM ñompanies WHERE c.id=?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM ñompanies WHERE id = ?";
	
	@Override
	public ArrayList<Company> allCompany() {
		ArrayList<Company> allCompany = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_COMPANY);
			rs = ps.executeQuery();
			while (rs.next()) {
				allCompany.add(createCompany(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return allCompany;
	}

	private Company createCompany(ResultSet rs) {
		Company company = new Company();
		try {
//			AddressDAO adrDAO = new AddressDAO();
//			TrackDAO tDAO = new TrackDAO();
//			LogisticianDAO lDAO = new LogisticianDAO();
//			DriverDAO dDAO = new DriverDAO();
			company.setId(rs.getInt("id"));
			company.setName(rs.getString("name"));
			company.setRegNumber(rs.getString("reg_number"));
//			company.setAddress(adrDAO.selectById(rs.getInt("address_id")));
//			company.setTracks(tDAO.allTrack());
//			company.setAllEmployee(lDAO.allLogistician(), dDAO.allDrivers());
			Address address = new Address();
			address.setId(rs.getInt("address_id"));
			company.setAddress(address);
		} catch (Exception e) {
			logger.log(Level.ERROR, "SQLException. Can not write in class: " + e);
		}
		return company;
	}

	@Override
	public Company selectById(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Company company = new Company();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, 1);
			rs = ps.executeQuery();
			rs.next();
			company = createCompany(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return company;
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
	public void createNewCompany(Company com) {
		throw new UnsupportedOperationException("method not create");
	}

}
