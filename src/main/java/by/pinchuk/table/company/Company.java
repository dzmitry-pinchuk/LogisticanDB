package by.pinchuk.table.company;

import java.util.ArrayList;

import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.entity.BaseEntity;
import by.pinchuk.table.people.Driver;
//import by.pinchuk.table.people.Employee;
import by.pinchuk.table.people.Logistician;
import by.pinchuk.table.transport.Track;

public class Company extends BaseEntity{
	
	private String name;
	private String regNumber;
	private Address address;
//	private ArrayList<? extends Employee> employees;
//	private ArrayList<Employee> employees = new ArrayList<>();
	private ArrayList<Track> tracks;
	private ArrayList<Logistician> logistican;
	private ArrayList<Driver> driver;
	
//	private ArrayList<ArrayList<? extends Employee>> employees = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRegNumber() {
		return regNumber;
	}
	
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
//	public ArrayList<ArrayList<? extends Employee>> getEmployees() {
//		return employees;
//	}
	
//	public void setEmployees(ArrayList<ArrayList<? extends Employee>> employees) {
//		this.employees = employees;
//	}
	
//	public void setAllEmployee(ArrayList<Logistician> logisticans, ArrayList<Driver> drivers) {
//		this.employees.add(logisticans);
//		this.employees.add(drivers);
		
//		this.employees.get(0).addAll(logisticans);
//		this.employees.get(1).addAll(drivers);
//		this.employees.addAll(logisticans);
//		this.employees.addAll(drivers);
//	}
	
	public ArrayList<Track> getTracks() {
		return tracks;
	}
	
	public ArrayList<Logistician> getLogisticanList() {
		return logistican;
	}

	public void setLogisticanList(ArrayList<Logistician> logistican) {
		this.logistican = logistican;
	}

	public ArrayList<Driver> getDriverList() {
		return driver;
	}

	public void setDriverList(ArrayList<Driver> driver) {
		this.driver = driver;
	}

	public void setTracks(ArrayList<Track> tracks) {
		this.tracks = tracks;
	}
	
	@Override
	public String toString() {
		return "Company [name=" + name + ", regNumber=" + regNumber + "Address"+ this.address.toString()
		+ "\n Tracks " + this.tracks + " \n Drivers "+ this.driver + "\n Logisticans "+ this.logistican +
			
				
		"] \n";
	}

}
