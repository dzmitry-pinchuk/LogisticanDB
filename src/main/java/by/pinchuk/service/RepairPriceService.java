package by.pinchuk.service;

import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.dao.addresses.IAddress;
import by.pinchuk.dao.people.DriverDAO;
import by.pinchuk.dao.people.IDriver;
import by.pinchuk.dao.transport.IService;
import by.pinchuk.dao.transport.ServiceDAO;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.payment.RepairPrice;
import by.pinchuk.table.people.Driver;
import by.pinchuk.table.transport.Service;


public class RepairPriceService {
	
//	private ServiceDAO sDAO = new ServiceDAO();
//	private DriverDAO dDAO = new DriverDAO();
//	private AddressDAO adrDAO = new AddressDAO();
	
	private IDriver dDAO;
	private IAddress adrDAO;
	private IService sDAO;
	
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
	

}
