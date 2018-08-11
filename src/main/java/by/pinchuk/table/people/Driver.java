package by.pinchuk.table.people;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;

import by.pinchuk.db.runner.JaxBDataAdapter;

@XmlRootElement(name = "Driver")
@XmlAccessorType (XmlAccessType.FIELD)
public class Driver extends Employee{
	
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "MM/dd/yyyy"
			)
	@XmlJavaTypeAdapter(JaxBDataAdapter.class)
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
