package com.samuk159.worstmovie.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Studio extends AbstractEntity {
	
	@NotBlank
	private String name;

	public Studio() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public Studio(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
