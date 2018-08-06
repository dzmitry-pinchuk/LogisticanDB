package by.pinchuk.table.people;

import com.fasterxml.jackson.annotation.JsonIgnore;

import by.pinchuk.table.company.Company;
import by.pinchuk.table.entity.BaseEntity;

public abstract class Employee extends BaseEntity {
	 
	private String firstName;
	private String lastName;
	private Integer salary;
	@JsonIgnore
	private Company company;
	
	public Employee() {
	}
	
	public Employee(String firstName, String lastName, int salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [id=" + super.getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
				+ "]";
	}
	
	
	
	
	

}
