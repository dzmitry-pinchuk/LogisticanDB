package by.pinchuk.table.addresses;

import by.pinchuk.table.entity.BaseEntity;

public class City extends BaseEntity{
	private String cityName;
	
	public City() {
	}
	
	public City(String cityName) {
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
