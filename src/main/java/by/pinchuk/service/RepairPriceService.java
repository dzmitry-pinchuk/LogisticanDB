package by.pinchuk.service;

import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.dao.people.DriverDAO;
import by.pinchuk.dao.transport.ServiceDAO;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.payment.RepairPrice;
import by.pinchuk.table.people.Driver;
import by.pinchuk.table.transport.Service;


public class RepairPriceService {
	
	private ServiceDAO sDAO = new ServiceDAO();
	private DriverDAO dDAO = new DriverDAO();
//	private ServiceService ss = new ServiceService();
	private AddressDAO adrDAO = new AddressDAO();
	
	public RepairPrice setRepairPriceField(RepairPrice rp) {
		Service service = rp.getService();
		service = sDAO.selectById(service.getId());
		Address address = service.getAddress();
		service.setAddress(adrDAO.selectById(address.getId()));
		rp.setService(service);
		Driver driver = rp.getDriver();
		rp.setDriver(dDAO.selectById(driver.getId()));
		return rp;
	}
	
//	public RepairPrice setRepairPriceField(RepairPrice rp) {
//		Service service = rp.getService();
//		rp.setService(ss.setServiceField(sDAO.selectById(service.getId())));
//		Driver driver = rp.getDriver();
//		rp.setDriver(dDAO.selectById(driver.getId()));
//		return rp;
//	}
	

}
