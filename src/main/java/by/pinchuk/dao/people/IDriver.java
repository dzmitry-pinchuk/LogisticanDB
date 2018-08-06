package by.pinchuk.dao.people;

import java.util.List;

import by.pinchuk.table.people.Driver;

public interface IDriver {
	
	public List<Driver> allDrivers();
	
	public Driver selectById(long id);
	
	public void deleteById(long id);
	
	public void createNewDriver(Driver dr);

}
