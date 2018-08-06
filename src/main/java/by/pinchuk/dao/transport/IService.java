package by.pinchuk.dao.transport;

import java.util.List;

import by.pinchuk.table.transport.Service;

public interface IService {
	
	public List<Service> allServices();
	
	public Service selectById(long id);
	
	public void deleteById(long id);
	
	public void createNewService(Service sr);

}
