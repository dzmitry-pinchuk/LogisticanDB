package by.pinchuk.db.runner;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBatisUtil {
	
	private static final Logger logger = LogManager.getLogger();
	private static SqlSessionFactory ssf;
	public final static String CONFIG_PATH = "config.xml";
	
	static {
		try {
			InputStream is = Resources.getResourceAsStream("config.xml");
			ssf = new SqlSessionFactoryBuilder().build(is);
			} catch (IOException e) {
				logger.log(Level.ERROR, "IOException: " + e.getMessage());
			}
	}

	public static SqlSessionFactory getSsf() {
		return ssf;
	}

}
