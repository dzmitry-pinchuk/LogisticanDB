package by.pinchuk.db.runner;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.connection.pool.ConnectionPool;
import by.pinchuk.dao.addresses.AddressDAO;
import by.pinchuk.dao.company.ConsigneeDAO;
import by.pinchuk.dao.payment.RepairPriceDAO;
import by.pinchuk.dao.people.CustomDAO;
import by.pinchuk.dao.people.ILogistician;
import by.pinchuk.dao.transport.ServiceDAO;
import by.pinchuk.db.actions.ConstractorThread;
import by.pinchuk.db.actions.ThreadExample;
import by.pinchuk.service.All;
import by.pinchuk.service.RepairPriceService;
import by.pinchuk.table.company.Company;
import by.pinchuk.table.payment.RepairPrice;
import by.pinchuk.table.people.Employee;
import by.pinchuk.table.people.Logistician;
import by.pinchuk.table.transport.Track;

public class Run {
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {

//		ConsigneeDAO c = new ConsigneeDAO();
//		logger.log(Level.INFO, c.selectById().toString());
		
//		System.out.println();
//		AdressDAO a = new AdressDAO();
//		logger.log(Level.INFO, a.allAdresses().toString());
		
				
//		ConstractorThread ct = new ConstractorThread();
//		ct.setMethodID(1);
//		Thread t1 = new Thread(ct);
//		t1.start();
//		
//		ct.setMethodID(2);
//		Thread t2 = new Thread(ct);
//		t2.start();
		
//		logger.log(Level.INFO, "new operation");
//		CustomDAO cas = new CustomDAO();
//		logger.log(Level.INFO, "allCastoms" + cas.allCustoms().toString());
		
//		cas.insert();
//		cas.update();
//		cas.delete();
//		logger.log(Level.INFO, "allCastoms" + cas.allCustoms().toString());
//		logger.log(Level.INFO, "allCastoms" + cas.selectById().toString());
		

//		Track t = new Track();
//		t.setId(1);
//		Logistician l = new Logistician();
		
//		RepairPriceService rps = new RepairPriceService();
//		rps.allRepairPriceByTrackID(3);
		

//		RepairPriceDAO rpDAO = new RepairPriceDAO();
//		logger.log(Level.INFO, rpDAO.allRepairPriceByTrackId(3));
		
//		ServiceDAO sDAO = new ServiceDAO();
//		logger.log(Level.INFO, sDAO.allServices());
		
//		AddressDAO adrDAO = new AddressDAO();
//		System.out.println(adrDAO.selectById(2));
		
		All all = new All();
		ArrayList<Company> allCompany = all.getAll();
		System.out.println(allCompany);
		
		
//		ILogistician lolo = MyBatisUtil.getSsf().openSession().getMapper(ILogistician.class);
//		Logistician log = lolo.selectById(1);
//		System.out.println(log);
		
	}

}
