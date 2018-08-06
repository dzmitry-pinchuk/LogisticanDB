package by.pinchuk.table.people;

import by.pinchuk.table.entity.BaseEntity;

public class Custom extends BaseEntity {
	
	private String firstName;
	private String lastName;
	private String department;
	private int payment;
	
	public Custom(String firsName, String lastName, String department, int payment) {
		this.firstName = firsName;
		this.lastName = lastName;
		this.department = department;
		this.payment = payment;
	}

	public Custom() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirsrtName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "\n Customs [id=" + getId() + ", firsName=" + firstName + ", lastName=" + lastName + ", department="
				+ department + ", payment=" + payment + "]";
	}
	
	
	


}
