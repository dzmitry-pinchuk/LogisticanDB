package by.pinchuk.service;

import java.util.ArrayList;

import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.dao.addresses.IAddress;
import by.pinchuk.dao.company.ConsigneeDAO;
import by.pinchuk.dao.company.IConsignee;
import by.pinchuk.dao.company.IShipper;
import by.pinchuk.dao.company.ShipperDAO;
import by.pinchuk.dao.people.CustomDAO;
import by.pinchuk.dao.people.ICustom;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.company.Consignee;
import by.pinchuk.table.company.Shipper;
import by.pinchuk.table.people.Logistician;

public class LogisticianService {
	
//	private CustomDAO cDAO = new CustomDAO();
//	private ShipperDAO sDAO = new ShipperDAO();
//	private ConsigneeDAO conDAO = new ConsigneeDAO();
//	private AddressDAO adrDAO = new AddressDAO();
	
	private IAddress adrDAO;
	private ICustom cDAO;
	private IConsignee conDAO;
	private IShipper sDAO;
	
	
	public Logistician setLogisticianField (Logistician logistican) {
	
		logistican.setCustomsList(cDAO.getCustomByLogistID(logistican.getId()));
		
		ArrayList<Shipper> shipperListByLogistID = sDAO.getShipperByLogistID(logistican.getId());

		for (Shipper shipper : shipperListByLogistID) {
			Address address = shipper.getAddress();
			shipper.setAddress(adrDAO.selectById(address.getId()));
		}
		logistican.setShipersList(shipperListByLogistID);
		
		ArrayList<Consignee> consigneeListByLogistID = conDAO.getConsigneeByLogistID(logistican.getId());
		for (Consignee consignee : consigneeListByLogistID) {
			Address address = consignee.getAddressCon();
			consignee.setAddressConn(adrDAO.selectById(address.getId()));
		}
		logistican.setConsigneesList(consigneeListByLogistID);
		
		return logistican;
	}

}
