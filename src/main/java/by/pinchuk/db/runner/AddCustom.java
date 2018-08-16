package by.pinchuk.db.runner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.dao.people.ICustom;
import by.pinchuk.table.people.Custom;

public class AddCustom {
	
	private static Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		ICustom icus = MyBatisUtil.getSsf().openSession(true).getMapper(ICustom.class);
		Custom custom = new Custom("svetlana", "svetlanova", "1", 12222);
		
		icus.createNewCustom(custom);
		logger.log(Level.INFO, custom);
		

	}

}
