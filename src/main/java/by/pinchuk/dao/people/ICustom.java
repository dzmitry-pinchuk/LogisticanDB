package by.pinchuk.dao.people;

import java.util.ArrayList;
import java.util.List;

import by.pinchuk.table.people.Custom;

public interface ICustom {

	public List<Custom> allCustoms();

	public Custom selectById(long id);

	public void deleteById(long id);

	public void createNewCustom(Custom cus);
	
	public ArrayList<Custom> getCustomByLogistID(long id);

}
