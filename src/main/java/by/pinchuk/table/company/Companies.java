package by.pinchuk.table.company;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class Companies {

	@XmlElement(name = "company")
	ArrayList<Company> list = new ArrayList<>();

	public Companies() {
		super();
	}

	public ArrayList<Company> getList() {
		return list;
	}

	public void setList(ArrayList<Company> list) {
		this.list = list;
	}

	public void add(Company company) {
		list.add(company);
	}

	@Override
	public String toString() {
		return "Companies [list=" + list + "]";
	}
	
	
}
