package by.pinchuk.table.addresses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import by.pinchuk.table.entity.BaseEntity;

//@XmlRootElement
//@XmlAccessorType (XmlAccessType.FIELD)
public class City extends BaseEntity{
	private String cityName;
	
	public City() {
	}
	
	public City(long cityID, String cityName) {
		setId(cityID);
		this.cityName = cityName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
//		return "Cities [id=" + id + ", citiesName=" + citiesName + "]";
		return cityName;
	}
	
}
