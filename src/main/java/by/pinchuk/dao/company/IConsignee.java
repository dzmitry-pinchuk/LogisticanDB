package by.pinchuk.dao.company;

import java.util.ArrayList;

import by.pinchuk.table.company.Consignee;

public interface IConsignee {

	public ArrayList<Consignee> allConsignee();

	public Consignee selectById(long id);

	public void deleteById(long id);

	public void createNewConsignee(Consignee con);

	public ArrayList<Consignee> getConsigneeByLogistID(Long id);
}
