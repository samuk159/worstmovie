package com.samuk159.worstmovie.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Studio extends AbstractEntity {
	
	@NotBlank
	private String name;
	
	@JsonIgnoreProperties("studios")
	@ManyToMany(mappedBy = "studios")
	//@Where(clause = "winner = true")
	private List<Movie> movies;

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
	
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Studio [name=" + name + "]";
	}
	
}
