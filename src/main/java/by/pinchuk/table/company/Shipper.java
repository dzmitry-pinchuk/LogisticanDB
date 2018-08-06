package by.pinchuk.table.company;

import by.pinchuk.table.addresses.Address;
import by.pinchuk.table.entity.BaseEntity;

public class Shipper extends BaseEntity{

	private String shipperName;
	private String phoneNumber;
	private Address address;
	
	public Shipper() {
	}
	
	public Shipper(String shipperName, String phoneNumber, Address adress) {
		this.shipperName = shipperName;
		this.phoneNumber = phoneNumber;
		this.address = adress;
	}
	
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
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
	
	@Override
	public String toString() {
		return "Shipper [id=" + getId() + ", shipper_name=" + shipperName + ", phone_number=" + phoneNumber
				+ ", addresses_id=" + address.toString() + "]";
	}
	
}
