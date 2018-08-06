package by.pinchuk.table.company;

import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.entity.BaseEntity;

public class Consignee extends BaseEntity{
	
	private String consigneeName;
	private String phoneNumber;
	public Address address;
	
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
	public void setPhone_number(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "\n Consignee [id=" + getId() + ", consigneeName=" + consigneeName + ", phoneNumber=" + phoneNumber
				+ ", adress=" + address.toString() + "] ";
	}
	

}
