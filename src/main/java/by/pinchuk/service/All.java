package by.pinchuk.service;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.pinchuk.dao.company.CompanyDAO;
import by.pinchuk.dao.company.ICompany;
import by.pinchuk.dao.company.IShipper;
import by.pinchuk.db.runner.MyBatisUtil;
import by.pinchuk.table.company.Company;

public class All {
	
	
	private CompanyService cs = new CompanyService();
//	private CompanyDAO cDAO = new CompanyDAO();
	private ICompany cDAO = MyBatisUtil.getSsf().openSession().getMapper(ICompany.class);
	private static final Logger logger = LogManager.getLogger();
	
	public ArrayList<Company> getAll() {
		ArrayList<Company> allCompany = cDAO.allCompany();
//		for (Company company : allCompany) {
//			cs.setCompanyField(company);
//		}
		return allCompany;
	}

}
