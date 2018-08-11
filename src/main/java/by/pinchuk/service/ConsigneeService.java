package by.pinchuk.service;

import by.pinchuk.dao.addresses.AddressDAO;
//import by.pinchuk.dao.company.ConsigneeDAO;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.company.Consignee;

public class ConsigneeService {
	
	private AddressDAO adrDAO = new AddressDAO();
	
	public Consignee setAddressConsignee(Consignee consignee) {
		Address address = consignee.getAddressCon();
		consignee.setAddressConn(adrDAO.selectById(address.getId()));
		return consignee;
	}

}
