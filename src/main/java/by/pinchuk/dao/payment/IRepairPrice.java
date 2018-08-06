package by.pinchuk.dao.payment;

import java.util.List;

import by.pinchuk.table.payment.RepairPrice;

public interface IRepairPrice {

	public List<RepairPrice> allRepairPrice();

	public RepairPrice selectById(long id);

	public void deleteById(long id);

	public void createNewRepairPrice(RepairPrice rp);

}
