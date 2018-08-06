package by.pinchuk.service;

import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.transport.Service;

public class ServiceService {
	
	private AddressDAO adrDAO = new AddressDAO();
	
	public Service setServiceField(Service service) {
		Address address = service.getAddress();
		service.setAddress(adrDAO.selectById(address.getId()));
		return service;
	}
	

}
