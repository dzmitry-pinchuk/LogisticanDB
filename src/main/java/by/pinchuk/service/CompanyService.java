package by.pinchuk.service;

import java.util.ArrayList;

import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.dao.addresses.CountryDAO;
import by.pinchuk.dao.addresses.IAddress;
import by.pinchuk.dao.addresses.ICountry;
import by.pinchuk.dao.company.ConsigneeDAO;
import by.pinchuk.dao.company.ICompany;
import by.pinchuk.dao.company.IConsignee;
import by.pinchuk.dao.company.IShipper;
import by.pinchuk.dao.company.ShipperDAO;
import by.pinchuk.dao.payment.CheckFaresDAO;
import by.pinchuk.dao.payment.ICheckFares;
import by.pinchuk.dao.payment.IRepairPrice;
import by.pinchuk.dao.payment.RepairPriceDAO;
import by.pinchuk.dao.people.CustomDAO;
import by.pinchuk.dao.people.DriverDAO;
import by.pinchuk.dao.people.ICustom;
import by.pinchuk.dao.people.IDriver;
import by.pinchuk.dao.people.ILogistician;
import by.pinchuk.dao.people.LogisticianDAO;
import by.pinchuk.dao.transport.IService;
import by.pinchuk.dao.transport.ITrack;
import by.pinchuk.dao.transport.ServiceDAO;
import by.pinchuk.dao.transport.TrackDAO;
import by.pinchuk.db.runner.MyBatisUtil;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.addresses.Country;
import by.pinchuk.table.company.Company;
import by.pinchuk.table.company.Consignee;
import by.pinchuk.table.company.Shipper;
import by.pinchuk.table.payment.CheckFare;
import by.pinchuk.table.payment.RepairPrice;
import by.pinchuk.table.people.Driver;
import by.pinchuk.table.people.Logistician;
import by.pinchuk.table.transport.Service;
import by.pinchuk.table.transport.Track;

public class CompanyService {
	
//	private AddressDAO adrDAO = new AddressDAO();
//	private TrackDAO tDAO = new TrackDAO();
//	private LogisticianDAO lDAO = new LogisticianDAO();
//	private DriverDAO dDAO = new DriverDAO();
//	private RepairPriceDAO rpDAO = new RepairPriceDAO();
//	private CheckFaresDAO cfDAO = new CheckFaresDAO();
//	private CountryDAO cDAO = new CountryDAO();
//	private ServiceDAO sDAO = new ServiceDAO();
//	private CustomDAO cusDAO = new CustomDAO();
//	private ConsigneeDAO conDAO = new ConsigneeDAO();
//	private ShipperDAO shDAO = new ShipperDAO();
	
//	private IAddress adrDAO;
//	private IDriver dDAO;
//	private ITrack tDAO;
//	private ILogistician lDAO;
//	private IRepairPrice rpDAO;
//	private ICheckFares cfDAO;
//	private ICountry cDAO;
//	private IService sDAO;
//	private ICustom cusDAO;
//	private IConsignee conDAO;
//	private IShipper shDAO;
	
	private IAddress adrDAO = MyBatisUtil.getSsf().openSession(true).getMapper(IAddress.class);
	private IDriver dDAO = MyBatisUtil.getSsf().openSession(true).getMapper(IDriver.class);
	private ITrack tDAO = MyBatisUtil.getSsf().openSession(true).getMapper(ITrack.class);
	private ILogistician lDAO = MyBatisUtil.getSsf().openSession(true).getMapper(ILogistician.class);
	private IRepairPrice rpDAO = MyBatisUtil.getSsf().openSession(true).getMapper(IRepairPrice.class);
	private ICheckFares cfDAO = MyBatisUtil.getSsf().openSession(true).getMapper(ICheckFares.class);
	private ICountry cDAO = MyBatisUtil.getSsf().openSession(true).getMapper(ICountry.class);
	private IService sDAO = MyBatisUtil.getSsf().openSession(true).getMapper(IService.class);
	private ICustom cusDAO = MyBatisUtil.getSsf().openSession(true).getMapper(ICustom.class);
	private IConsignee conDAO = MyBatisUtil.getSsf().openSession(true).getMapper(IConsignee.class);
	private IShipper shDAO = MyBatisUtil.getSsf().openSession(true).getMapper(IShipper.class);
	
	
	public Company setCompanyField(Company company) {
		Address address = company.getAddress();
		System.out.println(address);
		company.setAddress(adrDAO.selectById(address.getId()));
		
		ArrayList<Track> allTrackByCompany = tDAO.allTrack();
		for (Track track1 : allTrackByCompany) {
			Track track = track1;
			ArrayList<RepairPrice> allRepairPriceByTrackID = rpDAO.allRepairPriceByTrackId(track.getId());
			for (RepairPrice repairPrice : allRepairPriceByTrackID) {
				RepairPrice rp = repairPrice;
				Service service = rp.getService();
				service = sDAO.selectById(service.getId());
				Address address1 = service.getAddress();
				service.setAddress(adrDAO.selectById(address1.getId()));
				rp.setService(service);
				Driver driver = rp.getDriver();
				rp.setDriver(dDAO.selectById(driver.getId()));
				repairPrice = rp;
			}
			track.setRepairPrices(allRepairPriceByTrackID);
			ArrayList<CheckFare> allCheckFaresByTrackID = cfDAO.allCheckFaresByTrackID(track.getId());
			for (CheckFare checkFare : allCheckFaresByTrackID) {
				CheckFare cf = checkFare;
				Driver driver = cf.getDriver();
				cf.setDriver(dDAO.selectById(driver.getId()));
				Country country = cf.getCountry();
				cf.setCountry(cDAO.selectById(country.getId()));
				checkFare = cf;
			}
			track.setCheckFares(allCheckFaresByTrackID);
			track1 = track;
		}
		company.setTracksList(allTrackByCompany);
		
		ArrayList<Logistician> allLogisticianByCompany = lDAO.allLogistician();
		for (Logistician logistician1 : allLogisticianByCompany) {
			Logistician logistican = logistician1;
			
			logistican.setCustomsList(cusDAO.getCustomByLogistID(logistican.getId()));
			
			ArrayList<Shipper> shipperListByLogistID = shDAO.getShipperByLogistID(logistican.getId());
			for (Shipper shipper : shipperListByLogistID) {
				Address address2 = shipper.getAddress();
				shipper.setAddress(adrDAO.selectById(address2.getId()));
			}
			logistican.setShipersList(shipperListByLogistID);
			ArrayList<Consignee> consigneeListByLogistID = conDAO.getConsigneeByLogistID(logistican.getId());
			for (Consignee consignee : consigneeListByLogistID) {
				Address address3 = consignee.getAddressCon();
				consignee.setAddressConn(adrDAO.selectById(address3.getId()));
			}
			logistican.setConsigneesList(consigneeListByLogistID);
			
			logistician1 = logistican;
		}
		company.setLogisticanList(allLogisticianByCompany);
		
		company.setDriverList(dDAO.allDrivers());
		return company;
	}

	
	
//	public Company setCompanyField(Company company) {
//		Address address = company.getAddress();
//		company.setAddress(adrDAO.selectById(address.getId()));
//		ArrayList<Track> allTrackByCompany = tDAO.allTrack();
//		for (Track track : allTrackByCompany) {
//			ts.setTrackField(track);
//		}
//		company.setTracks(allTrackByCompany);
//		ArrayList<Logistician> allLogisticianByCompany = lDAO.allLogistician();
//		for (Logistician logistician : allLogisticianByCompany) {
//			ls.setLogisticianField(logistician);
//		}
//		company.setLogisticanList(allLogisticianByCompany);
//		company.setDriverList(dDAO.allDrivers());
//		return company;
//	}

}
