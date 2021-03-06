package by.pinchuk.table.addresses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import by.pinchuk.table.entity.BaseEntity;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Country extends BaseEntity{

	private String countryName;
	private Integer fare;
	private String roadQuality;
	
	public Country(long countryID, String countryName, int fare, String roadQuality) {
		setId(countryID);
		this.countryName = countryName;
		this.fare = fare;
		this.roadQuality = roadQuality;
	}

	public Country() {
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName1) {
		this.countryName = countryName1;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public String getRoadQality() {
		return roadQuality;
	}

	public void setRoadQuality(String roadQuality) {
		this.roadQuality = roadQuality;
	}

	@Override
	public String toString() {
//		return "Countries [id=" + id + ", countryName=" + countryName + ", fare=" + fare + ", roadQuality="
//				+ roadQuality + "]";
		return countryName;
	}
	
	public String toString2() {
		return "Countries [id=" + getId() + ", countryName=" + countryName + ", fare=" + fare + ", roadQuality="
				+ roadQuality + "]";
	}
	
	


}
