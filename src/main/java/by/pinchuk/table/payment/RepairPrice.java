package by.pinchuk.table.payment;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import by.pinchuk.db.runner.JaxBDataAdapter;
import by.pinchuk.table.entity.BaseEntity;
import by.pinchuk.table.people.Driver;
import by.pinchuk.table.transport.Service;
import by.pinchuk.table.transport.Track;

@XmlRootElement(name = "RepairPrice")
@XmlAccessorType (XmlAccessType.FIELD)
public class RepairPrice extends BaseEntity {
	
	private Service service;
	@JsonIgnore
	@XmlTransient
	private Track track;
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "MM/dd/yyyy"
			)
	@XmlJavaTypeAdapter(JaxBDataAdapter.class)
	private Date date;
	private Driver driver;

	public RepairPrice(Service service, Track track, Date date, Driver driver1) {
		super();
		this.service = service;
		this.track = track;
		this.date = date;
		driver = driver1;
	}

	public RepairPrice() {
		// TODO Auto-generated constructor stub
	}
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) {
		this.service = service;
	}

	@XmlTransient
	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
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

	public void setDriver(Driver driver1) {
		this.driver = driver1;
	}

	@Override
	public String toString() {
//		return "RepairPriñe [id=" + getId() + ", service=" + service + ", track=" + track + ", date=" + date
//				+ ", Driver=" + driver + "]";
		return "\n RepairPriñe [id=" + getId()  + ", date=" + date + ", service="+service +", Driver=" + driver + "] \n";
	}
	
	

}
