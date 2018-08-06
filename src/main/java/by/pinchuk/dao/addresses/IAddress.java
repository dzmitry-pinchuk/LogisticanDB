package by.pinchuk.dao.addresses;

import java.util.List;

import by.pinchuk.table.addresses.Address;

public interface IAddress {
	
	public List<Address> allAddresses();
	
	public Address selectById(long id);
	
	public void deleteById(long id);
	
	public void createAddress(Address adr);
	

}
