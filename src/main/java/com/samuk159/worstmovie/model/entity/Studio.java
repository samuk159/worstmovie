package com.samuk159.worstmovie.model.entity;

import java.util.LinkedList;
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
	private List<Movie> movies;

	public Studio() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public Studio(String name) {
		super();
		this.name = name;
	}
	
	public Studio(Long id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Movie> getMovies() {
		System.out.println("Studio.getMovies");
		System.out.println(movies);
		
		if (movies == null) {
			movies = new LinkedList<Movie>();
		}
		
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Studio [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studio other = (Studio) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
