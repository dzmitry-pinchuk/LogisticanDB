package by.pinchuk.service;

import java.util.ArrayList;

import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.dao.addresses.CountryDAO;
import by.pinchuk.dao.payment.CheckFaresDAO;
import by.pinchuk.dao.payment.RepairPriceDAO;
import by.pinchuk.dao.people.DriverDAO;
import by.pinchuk.dao.transport.ServiceDAO;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.addresses.Country;
import by.pinchuk.table.payment.CheckFare;
import by.pinchuk.table.payment.RepairPrice;
import by.pinchuk.table.people.Driver;
import by.pinchuk.table.transport.Service;
import by.pinchuk.table.transport.Track;

public class TrackService {
	
	private RepairPriceDAO rpDAO = new RepairPriceDAO();
	private CheckFaresDAO cfDAO = new CheckFaresDAO();
//	private RepairPriceService rps = new RepairPriceService();
//	private CheckFaresService cfs = new CheckFaresService();
	private CountryDAO cDAO = new CountryDAO();
	private DriverDAO dDAO = new DriverDAO();
	private ServiceDAO sDAO = new ServiceDAO();
	private AddressDAO adrDAO = new AddressDAO();
	
	public Track setTrackField(Track track) {
		
		ArrayList<RepairPrice> allRepairPriceByTrackID = rpDAO.allRepairPriceByTrackId(track.getId());
//		for (RepairPrice repairPrice : allRepairPriceByTrackID) {
//			rps.setRepairPriceField(repairPrice);
//		}
		for (RepairPrice repairPrice : allRepairPriceByTrackID) {
			RepairPrice rp = repairPrice;
			Service service = rp.getService();
			service = sDAO.selectById(service.getId());
			Address address = service.getAddress();
			service.setAddress(adrDAO.selectById(address.getId()));
			rp.setService(service);
			Driver driver = rp.getDriver();
			rp.setDriver(dDAO.selectById(driver.getId()));
			repairPrice = rp;
		}
		track.setRepairPrices(allRepairPriceByTrackID);
		
		ArrayList<CheckFare> allCheckFaresByTrackID = cfDAO.allCheckFaresByTrackID(track.getId());
//		for (CheckFare checkFare : allCheckFaresByTrackID) {
//			cfs.setCheckFareField(checkFare);
//		}
		for (CheckFare checkFare : allCheckFaresByTrackID) {
			CheckFare cf = checkFare;
			Driver driver = cf.getDriver();
			cf.setDriver(dDAO.selectById(driver.getId()));
			Country country = cf.getCountry();
			cf.setCountry(cDAO.selectById(country.getId()));
			checkFare = cf;
		}
		track.setCheckFares(allCheckFaresByTrackID);
		
		return track;
	}
	

}
