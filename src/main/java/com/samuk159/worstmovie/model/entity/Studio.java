package com.samuk159.worstmovie.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Studio {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	public Studio() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public Studio(String name) {
		super();
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
