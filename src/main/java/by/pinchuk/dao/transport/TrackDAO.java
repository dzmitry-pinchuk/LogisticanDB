package by.pinchuk.dao.transport;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.dao.other.JDBCAbstractDAO;
//import by.pinchuk.dao.payment.CheckFaresDAO;
//import by.pinchuk.dao.payment.RepairPriceDAO;
//import by.pinchuk.table.payment.RepairPrice;
import by.pinchuk.table.transport.Track;

public class TrackDAO extends JDBCAbstractDAO implements ITrack{

	private static Logger logger = LogManager.getLogger();
	public static final String SQL_SELECT_ALL_TRACS= "SELECT * FROM Tracs";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM Tracs WHERE id=?";
	public static final String SQL_DELETE_BY_ID = "DELETE FROM tracs WHERE id = ?";
	public static final String SQL_CREATE_NEW_TRACK = "INSERT INTO tracs (brand, model, carrying_capacity, date_of_service, reg_number) VALUES (?, ?, ?, ?, ?)";

	
	@Override
	public ArrayList<Track> allTrack() {
		ArrayList<Track> tracks = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_TRACS);
			rs = ps.executeQuery();
			while (rs.next()) {
				tracks.add(createTrack(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return tracks;
	}

	@Override
	public Track selectById(long id) {
		Track track = new Track();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			track = createTrack(rs);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from field: " + e);
		} finally {
			endOperation(ps, conn, rs);
		}
		return track;
	}
	
	private Track createTrack(ResultSet rs) {
		Track track = new Track();
		try {
//			RepairPriceDAO rpDAO = new RepairPriceDAO();
//			CheckFaresDAO cfDAO = new CheckFaresDAO();
			track.setId(rs.getInt("id"));
			track.setBrand(rs.getString("brand"));
			track.setModel(rs.getString("model"));
			track.setCarryingCapacity(rs.getInt("carrying_capacity"));
			track.setDateOfService(rs.getDate("date_of_service"));
			track.setRegNumber(rs.getInt("reg_number"));
//			track.setRepairPrices(rpDAO.allRepairPriceByTrackId(rs.getInt("id")));
//			track.setCheckFares(cfDAO.allCheckFaresByTrackID(rs.getInt("id")));

			
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException. Can not reaf from ResultSet: " + e);
		}
		return track;
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
	public void createNewTrack(Track tr) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL_CREATE_NEW_TRACK);
			ps.setString(1, tr.getBrand());
			ps.setString(2, tr.getModel());
			ps.setInt(3, tr.getCarryingCapacity());
			ps.setDate(4, (Date) tr.getDateOfService());
			ps.setInt(5, tr.getRegNumber());
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
