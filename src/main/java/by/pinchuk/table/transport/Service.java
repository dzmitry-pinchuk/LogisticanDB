package by.pinchuk.table.transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.entity.BaseEntity;

@XmlRootElement(name = "Service")
@XmlAccessorType (XmlAccessType.FIELD)
public class Service extends BaseEntity {
	
	private String serviceName;
	private String phoneNumber;
	private Address address;
	
	public Service(String serviceName, String phoneNumber, Address address) {
		this.serviceName = serviceName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public Service() {
	}

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setAddress(String countryName, String cityName, String streetName) {
		this.address.setStreetString(streetName);
		this.address.setCountryString(countryName);
		this.address.setCityString(cityName);
	}
	
	@Override
	public String toString() {
		return "\n Services [id=" + getId() + ", serviceName=" + serviceName + ", phoneNumber=" + phoneNumber
				+ ", adresses=" + address + "]";
//		return "Services [id=" + getId() + ", serviceName=" + serviceName + ", phoneNumber=" + phoneNumber
//				 + "]";
	}

	

}
