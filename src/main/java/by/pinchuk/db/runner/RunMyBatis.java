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

import by.pinchuk.dao.addresses.IAddress;
import by.pinchuk.dao.addresses.ICountry;
import by.pinchuk.dao.company.IConsignee;
import by.pinchuk.dao.company.IShipper;
import by.pinchuk.dao.people.ICustom;
import by.pinchuk.dao.people.ILogistician;
import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.addresses.Country;
import by.pinchuk.table.company.Consignee;
import by.pinchuk.table.company.Shipper;
import by.pinchuk.table.people.Custom;
import by.pinchuk.table.people.Logistician;

public class RunMyBatis {

	public static void main(String[] args) {
		Logger logger = LogManager.getLogger();
		try {
			SqlSessionFactory ssf;
			ICustom cusMapper;
			IAddress addMapper;
			
			
			InputStream is = Resources.getResourceAsStream("config.xml");
//			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
			ssf = new SqlSessionFactoryBuilder().build(is);
			
//			SqlSession s = ssf.openSession();
//			Custom custom = s.selectOne(statement, parameter);
			
//			SqlSessionTemplate sst = new SqlSessionTemplate(ssf);
//			ICustom cdao = sst.getMapper(ICustom.class);
			
			cusMapper = ssf.openSession().getMapper(ICustom.class);
			addMapper = ssf.openSession().getMapper(IAddress.class);
			ICountry couMapper = ssf.openSession().getMapper(ICountry.class);
			IConsignee cons = ssf.openSession().getMapper(IConsignee.class);
			IShipper ship = ssf.openSession().getMapper(IShipper.class);
			ILogistician lo = ssf.openSession().getMapper(ILogistician.class);
			
			
//			-------------------------------------------
			
//			Custom cus = cusMapper.selectById(2);
//			System.out.println(cus.toString());
			
//			ArrayList<Custom> allCust = (ArrayList<Custom>) cusMapper.allCustoms();
//			System.out.println(allCust);
			
//			ArrayList<Custom> allCustByID = (ArrayList<Custom>) cusMapper.getCustomByLogistID(1);
//			System.out.println(allCustByID);
			
//			ArrayList<Address> allAdd = (ArrayList<Address>) addMapper.allAddresses();
//			System.out.println(allAdd);
			
//			Address adr = addMapper.selectById(4);
//			System.out.println(adr.toString());
			
//			Country cou = couMapper.selectById(1);
//			System.out.println(cou.toString2());
			
//			Consignee con = cons.selectById(1);
//			System.out.println(con);
			
//			ArrayList<Consignee> allCon = cons.allConsignee();
//			System.out.println(allCon);
			
//			Shipper sh = ship.selectById(1);
//			System.out.println(sh); 
			
			Logistician log = lo.selectById(1);
			System.out.println(lo);
			
		} catch (IOException e) {
			logger.log(Level.ERROR, "IOException: " + e.getMessage());
		} 
		
		
		
		
		
		
	}

}
