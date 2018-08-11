package by.pinchuk.table.people;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import by.pinchuk.db.runner.JaxBDataAdapter;
import by.pinchuk.table.company.Consignee;
import by.pinchuk.table.company.Shipper;

@XmlRootElement(name = "Logistician")
@XmlAccessorType (XmlAccessType.FIELD)
public class Logistician extends Employee{

	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "MM/dd/yyyy"
			)
	@XmlJavaTypeAdapter(JaxBDataAdapter.class)
	private Date yearOfStartWork;
	@JsonIgnore
	private Employee employee;
	@XmlElementWrapper(name="consignees")
	@XmlElement(name="consignee")
	private ArrayList<Consignee> consignees;
	@XmlElementWrapper(name="shipers")
	@XmlElement(name="shiper")
	private ArrayList<Shipper> shipers;
	@XmlElementWrapper(name="customs")
	@XmlElement(name="custom")
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
	
	@XmlTransient
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public ArrayList<Consignee> getConsigneesList() {
		return consignees;
	}

	public void setConsigneesList(ArrayList<Consignee> consignees) {
		this.consignees = consignees;
	}

	public ArrayList<Shipper> getShipersList() {
		return shipers;
	}

	public void setShipersList(ArrayList<Shipper> shipers) {
		this.shipers = shipers;
	}

	public ArrayList<Custom> getCustomsList() {
		return customs;
	}

	public void setCustomsList(ArrayList<Custom> customs) {
		this.customs = customs;
	}

	@Override
	public String toString() {
		return "\n Logisticians [id=" + getId() + ", yearOfStartWork=" + yearOfStartWork + " "+ super.toString() + 
				"\n consignees" + consignees + "\n shipers" + shipers + "\n customs" + customs +"]";
	}
	
}
