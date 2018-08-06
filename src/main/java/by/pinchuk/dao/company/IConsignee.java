package by.pinchuk.dao.company;

import java.util.List;

import by.pinchuk.table.company.Consignee;

public interface IConsignee {

	public List<Consignee> allConsignee();

	public Consignee selectById(long id);

	public void deleteById(long id);

	public void createNewConsignee(Consignee con);

}
