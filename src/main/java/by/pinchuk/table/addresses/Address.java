package by.pinchuk.table.addresses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import by.pinchuk.table.entity.BaseEntity;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Address extends BaseEntity{
	private Country country;
	private City city;
	private Street street;
	
	public Address() {
	}
	
	public Address(long id) {
		setId(id);
	}
	

	public Address(Country country, City city, Street street) {
		this.country = country;
		this.city = city;
		this.street = street;
	}

	public Address(String countryName, String citiesName, String streetName) {
		this.country.setCountryName(countryName);
		this.city.setCityName(citiesName);
		this.street.setStreetName(streetName);
		
	}

	public void setAdress(String countr, String cit, String str) {
		this.country.setCountryName(countr);
		this.city.setCityName(cit);
		this.street.setStreetName(str);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public void setCountryString(String country1) {
		this.country.setCountryName(country1);
	}

	public City getCity() {
		return city;
	}

	public void setCity(City cit) {
		this.city = cit;
	}
	
	public void setCityString(String cit) {
		city.setCityName(cit);
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}
	
	public void setStreetString(String str) {
		this.street.setStreetName(str);
	}

	@Override
	public String toString() {
//		return "Addresses [id=" + id + ", country=" + country.toString() + ", citiy=" + city.toString() + ", street="
//				+ street.toString() + "]";
		return "\n Addresses [id="+ getId() +" country=" + country + ", citiy=" + city + ", street="
		+ street + "] \n";
	}
	
	
	


}
