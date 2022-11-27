package com.samuk159.worstmovie.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Producer extends AbstractEntity {
	
	@NotBlank
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "producers")
	@Where(clause = "winner = true")
	private List<Movie> movies;
	
	public Producer() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public Producer(String name) {
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
	
}
