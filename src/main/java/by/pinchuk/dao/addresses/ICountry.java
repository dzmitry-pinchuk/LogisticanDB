package by.pinchuk.dao.addresses;

import java.util.List;

import by.pinchuk.table.addresses.Country;

public interface ICountry {

	public List<Country> allCountry();
	
	public Country selectById(long id);
	
	public void deleteById(long id);
	
	public void createAddress(Country country);
	
}
