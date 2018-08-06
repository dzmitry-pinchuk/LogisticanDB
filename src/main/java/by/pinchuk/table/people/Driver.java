package by.pinchuk.table.people;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Driver extends Employee{
	
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "MM/dd/yyyy"
			)
	private Date licenseYear;
	
	public Driver() {
		super();
	}
	
	public Driver(String firstName, String lastName, int salary, Date licenseYear) {
		super(firstName, lastName, salary);
		this.licenseYear = licenseYear;
	}

	public Date getLicenseYear() {
		return licenseYear;
	}
	public void setLicenseYear(Date licenseYear) {
		this.licenseYear = licenseYear;
	}

	@Override
	public String toString() {
		return "Drivers [id=" + getId() + ", licenseYear=" + licenseYear + super.toString() + "]";
	}
	
	

	
}
