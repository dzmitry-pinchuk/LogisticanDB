package by.pinchuk.db.runner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;

import by.pinchuk.dao.people.ICustom;
import by.pinchuk.table.people.Custom;

public class RunMyBatis {

	public static void main(String[] args) {
		Logger logger = LogManager.getLogger();
//		final String PATH = "\\Logisticans\\src\\main\\resources\\config.xml";
		try {
			SqlSessionFactory ssf;
			ICustom cusMapper;
			
			InputStream is = Resources.getResourceAsStream("config.xml");
//			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
			ssf = new SqlSessionFactoryBuilder().build(is);
			
//			SqlSession s = ssf.openSession();
//			Custom custom = s.selectOne(statement, parameter);
			
//			SqlSessionTemplate sst = new SqlSessionTemplate(ssf);
//			ICustom cdao = sst.getMapper(ICustom.class);
			
			cusMapper = ssf.openSession().getMapper(ICustom.class);
//			-------------------------------------------
			
//			Custom cus = cusMapper.selectById(2);
//			System.out.println(cus.toString());
			
//			ArrayList<Custom> allCust = (ArrayList<Custom>) cusMapper.allCustoms();
//			System.out.println(allCust);
			
//			ArrayList<Custom> allCustByID = (ArrayList<Custom>) cusMapper.getCustomByLogistID(1);
//			System.out.println(allCustByID);
			
		} catch (IOException e) {
			logger.log(Level.ERROR, "IOException: " + e.getMessage());
		}
		
		
		
		
		
		
	}

}
