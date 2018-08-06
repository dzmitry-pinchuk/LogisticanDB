package by.pinchuk.table.payment;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import by.pinchuk.table.addresses.Country;
import by.pinchuk.table.entity.BaseEntity;
import by.pinchuk.table.people.Driver;
import by.pinchuk.table.transport.Track;

public class CheckFare extends BaseEntity{

	@JsonIgnore
	private Track track;
	private Country country;
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "MM/dd/yyyy"
			)
	private Date date;
	private Driver driver;
	
	public CheckFare(Track track, Country country, Date date, Driver driver1) {
		this.track = track;
		this.country = country;
		this.date = date;
		this.driver = driver1;
	}

	public CheckFare() {
		super();
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "CheckFares [id=" + getId() + ", country=" + country + ", date=" + date
				+ ", Drivers_id=" + driver + "] \n";
	}
	
	


}
