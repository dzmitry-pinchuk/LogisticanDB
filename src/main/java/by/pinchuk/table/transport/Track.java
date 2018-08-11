package by.pinchuk.table.transport;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import by.pinchuk.db.runner.JaxBDataAdapter;
import by.pinchuk.table.company.Company;
import by.pinchuk.table.entity.BaseEntity;
import by.pinchuk.table.payment.CheckFare;
import by.pinchuk.table.payment.RepairPrice;

@XmlRootElement(name = "Track")
@XmlAccessorType (XmlAccessType.FIELD)
public class Track extends BaseEntity{
	
	private String brand;
	private String model;
	private int carryingCapacity;
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "MM/dd/yyyy"
			)
	@XmlJavaTypeAdapter(JaxBDataAdapter.class)
	private Date dateOfService;
	private int regNumber;
	@JsonIgnore
	private Company company;
	private ArrayList<CheckFare> checkFares;
	private ArrayList<RepairPrice> repairPrices;
	
	
	public Track(String brand, String model, int carryingCapacity, Date dateOfService, int regNumber) {
		this.brand = brand;
		this.model = model;
		this.carryingCapacity = carryingCapacity;
		this.dateOfService = dateOfService;
		this.regNumber = regNumber;
	}
	
	public Track() {
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getCarryingCapacity() {
		return carryingCapacity;
	}
	public void setCarryingCapacity(int carryingCapacity) {
		this.carryingCapacity = carryingCapacity;
	}
	public Date getDateOfService() {
		return dateOfService;
	}
	public void setDateOfService(Date dateOfService) {
		this.dateOfService = dateOfService;
	}
	public int getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(int regNumber) {
		this.regNumber = regNumber;
	}
	
	public ArrayList<CheckFare> getCheckFares() {
		return checkFares;
	}

	public void setCheckFares(ArrayList<CheckFare> checkFares) {
		this.checkFares = checkFares;
	}

	public ArrayList<RepairPrice> getRepairPrices() {
		return repairPrices;
	}

	public void setRepairPrices(ArrayList<RepairPrice> repairPrices) {
		this.repairPrices = repairPrices;
	}
	
	@XmlTransient
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "\n Tracs [id=" + getId() + ", brand=" + brand + ", model=" + model + ", carryingCapacity=" + carryingCapacity
				+ ", dateOfService=" + dateOfService + ", regNumber=" + regNumber +
				"checkFares" + this.checkFares + "repairPrices "+ this.repairPrices +
				"]";
	}

	

}
