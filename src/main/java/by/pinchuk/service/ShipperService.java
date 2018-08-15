package by.pinchuk.service;

//import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.dao.addresses.IAddress;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.company.Shipper;

public class ShipperService {
	
//	private AddressDAO adrDAO = new AddressDAO();
	private IAddress adrDAO;
	
	public Shipper setAddressShipper(Shipper shipper) {
		Address address = shipper.getAddress();
		shipper.setAddress(adrDAO.selectById(address.getId()));
		return shipper;
	}

}
