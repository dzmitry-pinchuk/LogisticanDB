package by.pinchuk.dao.company;

import java.util.List;

import by.pinchuk.table.company.Shipper;

public interface IShipper {
	
	public List<Shipper> allShipper();
	
	public Shipper selectById(long id);

	public void deleteById(long id);

	public void createNewShipper(Shipper sh);
	
	
}
