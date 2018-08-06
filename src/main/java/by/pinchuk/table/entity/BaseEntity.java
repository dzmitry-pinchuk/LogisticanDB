package by.pinchuk.table.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseEntity {
	
	@JsonIgnore
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

}
