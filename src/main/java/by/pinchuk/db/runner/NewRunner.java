package by.pinchuk.db.runner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.pinchuk.service.All;
import by.pinchuk.table.company.Company;

public class NewRunner {
	public static void main(String[] args) {
		Logger logger = LogManager.getLogger();
		
		All all = new All();
		ArrayList<Company> allCompany = all.getAll();
		
		
		final String PATH = "src\\main\\resources\\data.json";
		ObjectMapper om = new ObjectMapper();
	
		try {
			om.writeValue(new File(PATH), allCompany);
		} catch (JsonGenerationException e) {
			logger.log(Level.ERROR, "JsonGenerationException: " + e);
		} catch (JsonMappingException e) {
			logger.log(Level.ERROR, "JsonMappingException: " + e);
		} catch (IOException e) {
			logger.log(Level.ERROR, "IOException: " + e);
		}
	}
}
