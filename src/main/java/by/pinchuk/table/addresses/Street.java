package by.pinchuk.table.addresses;

import by.pinchuk.table.entity.BaseEntity;

public class Street extends BaseEntity{
	
	private String streetName;
	
	public Street( String streetName) {
		this.streetName = streetName;
	}

	public Street() {
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Override
	public String toString() {
//		return "Cities [id=" + id + ", streetName=" + streetName + "]";
		return streetName;
	}
	

}
