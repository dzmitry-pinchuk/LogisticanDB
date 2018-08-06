package by.pinchuk.table.people;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import by.pinchuk.table.company.Consignee;
import by.pinchuk.table.company.Shipper;

public class Logistician extends Employee{

	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "MM/dd/yyyy"
			)
	private Date yearOfStartWork;
	@JsonIgnore
	private Employee employee;
	private ArrayList<Consignee> consignees;
	private ArrayList<Shipper> shipers;
	private ArrayList<Custom> customs;
	
	public Logistician(Employee employee1, String firstName, String lastName, int salary, Date yearOfStartWork ) {
		super(firstName, lastName, salary);
		this.yearOfStartWork = yearOfStartWork;
		this.employee = employee1;
	}
	
	public Logistician() {
	}
	
	public Date getYearOfStartWork() {
		return yearOfStartWork;
	}
	public void setYearOfStartWork(Date yearOfStartWork) {
		this.yearOfStartWork = yearOfStartWork;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public ArrayList<Consignee> getConsignees() {
		return consignees;
	}

	public void setConsignees(ArrayList<Consignee> consignees) {
		this.consignees = consignees;
	}

	public ArrayList<Shipper> getShipers() {
		return shipers;
	}

	public void setShipers(ArrayList<Shipper> shipers) {
		this.shipers = shipers;
	}

	public ArrayList<Custom> getCustoms() {
		return customs;
	}

	public void setCustoms(ArrayList<Custom> customs) {
		this.customs = customs;
	}

	@Override
	public String toString() {
		return "\n Logisticians [id=" + getId() + ", yearOfStartWork=" + yearOfStartWork + " "+ super.toString() + 
				"\n consignees" + consignees + "\n shipers" + shipers + "\n customs" + customs +"]";
	}
	
}
