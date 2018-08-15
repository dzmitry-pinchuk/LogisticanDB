package by.pinchuk.db.runner;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.service.All;
import by.pinchuk.table.company.Companies;
import by.pinchuk.table.company.Company;

public class RunJaxB {

	public static void main(String[] args) {
		Logger logger = LogManager.getLogger();
		final String PATH = "src/main/resources/JaxBGenerated.xml";
		All all = new All();
		ArrayList<Company> allCompany = all.getAll();
		Companies comp = new Companies();
		comp.setList(allCompany);
		try {
			JAXBContext context = JAXBContext.newInstance(Companies.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(comp, new File(PATH));
			logger.log(Level.INFO, "All done");
		} catch (JAXBException e) {
			logger.log(Level.ERROR, "JAXBException: " + e);
		}

		
	}
}
