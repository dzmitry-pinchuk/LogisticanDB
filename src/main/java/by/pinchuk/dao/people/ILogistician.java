package by.pinchuk.dao.people;

import java.util.List;

import by.pinchuk.table.people.Logistician;

public interface ILogistician {
	public List<Logistician> allLogistician();
	
	public Logistician selectById(long id);
	
	public void deleteById(long id);
	
	public void createNewLogistician(Logistician log);

}
