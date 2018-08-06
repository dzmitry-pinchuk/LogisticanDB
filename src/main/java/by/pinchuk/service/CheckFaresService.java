package by.pinchuk.service;

import by.pinchuk.dao.addresses.CountryDAO;
import by.pinchuk.dao.people.DriverDAO;
//import by.pinchuk.dao.transport.TrackDAO;
import by.pinchuk.table.addresses.Country;
import by.pinchuk.table.payment.CheckFare;
import by.pinchuk.table.people.Driver;
//import by.pinchuk.table.transport.Track;

public class CheckFaresService {
	
//	private TrackDAO tDAO = new TrackDAO();
	private DriverDAO dDAO = new DriverDAO();
	private CountryDAO cDAO = new CountryDAO();
	
	public CheckFare setCheckFareField(CheckFare cf) {
		Driver driver = cf.getDriver();
		cf.setDriver(dDAO.selectById(driver.getId()));
		Country country = cf.getCountry();
		cf.setCountry(cDAO.selectById(country.getId()));
//		Track track = cf.getTrack();
//		cf.setTrack(tDAO.selectById(track.getId()));
		return cf;
	}
	
	

}
