package by.pinchuk.table.company;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.entity.BaseEntity;
import by.pinchuk.table.people.Driver;
//import by.pinchuk.table.people.Employee;
import by.pinchuk.table.people.Logistician;
import by.pinchuk.table.transport.Track;

@XmlRootElement(name = "Company")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "regNumber", "address", "logistican",  "driver", "tracks"})
public class Company extends BaseEntity{
	
	private String name;
	private String regNumber;
	private Address address;
	@XmlElementWrapper(name="tracks")
	@XmlElement(name="track")
	private ArrayList<Track> tracks;
	@XmlElementWrapper(name="logisticans")
	@XmlElement(name="logistican")
	private ArrayList<Logistician> logistican;
	@XmlElementWrapper(name="drivers")
	@XmlElement(name="driver")
	private ArrayList<Driver> driver;
	
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
	
	public ArrayList<Track> getTracksList() {
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

	public void setTracksList(ArrayList<Track> tracks) {
		this.tracks = tracks;
	}
	
	@Override
	public String toString() {
		return "Company [name=" + name + ", regNumber=" + regNumber + "Address"+ this.address.toString()
		+ "\n Tracks " + this.tracks + " \n Drivers "+ this.driver + "\n Logisticans "+ this.logistican +
			
				
		"] \n";
	}

}
