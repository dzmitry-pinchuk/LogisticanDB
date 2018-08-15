package by.pinchuk.dao.company;

import java.util.ArrayList;

import by.pinchuk.table.company.Company;

public interface ICompany {

	public ArrayList<Company> allCompany();

	public Company selectById(long id);

	public void deleteById(long id);

	public void createNewCompany(Company com);
	
	
	
}
