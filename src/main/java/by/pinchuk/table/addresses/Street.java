package by.pinchuk.table.addresses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import by.pinchuk.table.entity.BaseEntity;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Street extends BaseEntity{
	
	private String streetName;
	
	public Street(long streetID, String streetName) {
		setId(streetID);
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
