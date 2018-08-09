package by.pinchuk.dao.people;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.dao.other.JDBCAbstractDAO;
import by.pinchuk.table.people.Custom;

public class CustomDAO extends JDBCAbstractDAO implements ICustom {

	private static Logger logger = LogManager.getLogger();
	private static final String SQL_SELECT_ALL_CUSTOM = "SELECT * FROM customs";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM Customs WHERE ID=?";
	private static final String SQL_INSERT = "INSERT customs (`first_name`, `last_name`, `department`, `payment`) VALUES ('vasia', 'ivanov', 11, 5000)";
	private static final String SQL_UPDATE = "UPDATE customs SET first_name ='masha' WHERE first_name = 'маша'";
	private static final String SQL_DELETE = "DELETE FROM customs WHERE department = 11";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM customs WHERE id = ?";
	private static final String SQL_CREATE_NEW_CUSTOM = "INSERT INTO customs (first_name, last_name, department, payment) VALUES (?,?,?,?)";
	private static final String SQL_SELECT_CUSTOM_BY_LOGIST_ID = "SELECT * FROM logisticians_has_customs WHERE Logisticians_id = ?";

	private ArrayList<Custom> customs = new ArrayList<>();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public ArrayList<Custom> allCustoms() {
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_CUSTOM);
			rs = ps.executeQuery();
			while (rs.next()) {
				customs.add(createCustom(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return customs;
	}

	@Override
	public Custom selectById(long id) {
		Custom custom = new Custom();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			custom = createCustom(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return custom;
	}

	private Custom createCustom(ResultSet rs) {
		Custom custom = new Custom();
		try {
			custom.setId(rs.getInt("id"));
			custom.setFirsrtName(rs.getString("first_name"));
			custom.setLastName(rs.getString("last_name"));
			custom.setDepartment(rs.getString("department"));
			custom.setPayment(rs.getInt("payment"));
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from ResultSet: " + e);
		}
		return custom;
	}

	public void insert() {
		conn = getConnection();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_INSERT);
			ps.execute();
			logger.log(Level.INFO, "successfully added");
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not write from field: " + e);
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

	public void update() {
		conn = getConnection();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_UPDATE);
			ps.execute();
			logger.log(Level.INFO, "successfully update");
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not write from field: " + e);
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

	public void delete() {
		conn = getConnection();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_DELETE);
			ps.execute();
			logger.log(Level.INFO, "successfully delete");
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not write from field: " + e);
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
	public void createNewCustom(Custom cus) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_CREATE_NEW_CUSTOM);
			ps.setString(1, cus.getFirstName());
			ps.setString(2, cus.getLastName());
			ps.setString(3, cus.getDepartment());
			ps.setInt(4, cus.getPayment());
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

	public ArrayList<Custom> getCustomByLogistID(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_CUSTOM_BY_LOGIST_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				customs.add(selectById(rs.getInt("Customs_id")));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not read from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return customs;
	}

}
