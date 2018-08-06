package by.pinchuk.db.runner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.pinchuk.dao.company.CompanyDAO;
import by.pinchuk.dao.company.ConsigneeDAO;
import by.pinchuk.dao.people.LogisticianDAO;
import by.pinchuk.table.company.Company;
import by.pinchuk.table.people.Logistician;

public class Runner {

	public static void main(String[] args) {
		Logger logger = LogManager.getLogger();
		final String PATH = "src\\main\\resources\\data.json";
		ObjectMapper om = new ObjectMapper();
		
//		ConsigneeDAO conDAO = new ConsigneeDAO();
//		logger.log(Level.INFO, conDAO.getConsigneeByLogistID(2));
		
		List<Company> allCompany = new ArrayList<>();
		CompanyDAO cDAO = new CompanyDAO();
		allCompany = cDAO.allCompany();
		
//		ArrayList<Logistician> allLogist = new ArrayList<>();
//		LogisticianDAO lDAO = new LogisticianDAO();
//		logger.log(Level.INFO, "start operation");
//		allLogist = lDAO.allLogistician();
		

		try {
			om.writeValue(new File(PATH), allCompany);
//			om.writeValue(new File(PATH), allLogist);
		} catch (JsonGenerationException e) {
			logger.log(Level.ERROR, "JsonGenerationException: " + e);
		} catch (JsonMappingException e) {
			logger.log(Level.ERROR, "JsonMappingException: " + e);
		} catch (IOException e) {
			logger.log(Level.ERROR, "IOException: " + e);
		}
		logger.log(Level.INFO, "all done");
		

		

	}

}
