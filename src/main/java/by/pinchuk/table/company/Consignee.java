package by.pinchuk.table.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.entity.BaseEntity;

@XmlRootElement(name = "Consignee")
@XmlAccessorType (XmlAccessType.FIELD)
public class Consignee extends BaseEntity{
	
	private String consigneeName;
	private String phoneNumber;
	private Address address;
	
	public Consignee(String consigneeName, String phoneNumber, Address address) {
		this.consigneeName = consigneeName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public Consignee() {
	}

	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddressCon() {
		return address;
	}
	public void setAddressConn(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "\n Consignee [id=" + getId() + ", consigneeName=" + consigneeName + ", phoneNumber=" + phoneNumber
				+ ", adress=" + address.toString() + "] ";
	}
	

}
