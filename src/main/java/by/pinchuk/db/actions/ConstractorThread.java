package by.pinchuk.db.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.dao.company.ConsigneeDAO;

public class ConstractorThread extends Thread{
	
	private List<Object> result = new ArrayList<Object>();
	private int methodID;
	private static Logger logger = LogManager.getLogger();
	
	public List<?> getResult() {
		return result;
	}
	public int getMethodID() {
		return methodID;
	}

	public void setMethodID(int methodID) {
		this.methodID = methodID;
	}

//	public void run() {
//		switch (methodID) {
//		case 1:
//			ConsigneeDAO c1 = new ConsigneeDAO();
//			logger.log(Level.INFO, c1.allConsignee());
//			break;
//		case 2:
//			ConsigneeDAO c2 = new ConsigneeDAO();
//			logger.log(Level.INFO, c2.selectById());
//			break;
//		default:
//			logger.log(Level.ERROR, "Can't run method. Check methodID");
//			break;
//		}
////		logger.log(Level.INFO, "RESULT: " + getResult().toString());	
//	}
	
	
	public ConstractorThread(String name) {
		super(name);
	}

	public void run() {
		String name = getName();
		logger.log(Level.INFO, "Start THRESD " + name);
		switch (name) {
		case "first":
			ConsigneeDAO c1 = new ConsigneeDAO();
			logger.log(Level.INFO, c1.allConsignee());
			break;
		case "second":
//			ConsigneeDAO c2 = new ConsigneeDAO();
//			logger.log(Level.INFO, c2.selectById());
			break;
		default:
			logger.log(Level.ERROR, "Can't run method. Check name");
			break;
		}	
	}
	
}
