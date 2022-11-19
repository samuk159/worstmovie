package com.samuk159.worstmovie.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Movie {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Min(0)
	private Integer releaseYear;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String studios;
	
	@NotBlank
	private String producers;
	
	private boolean winner = false;
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Movie(Long id, Integer releaseYear, String title, String studios, String producers, boolean winner) {
		super();
		this.id = id;
		this.releaseYear = releaseYear;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStudios() {
		return studios;
	}
	public void setStudios(String studios) {
		this.studios = studios;
	}
	public String getProducers() {
		return producers;
	}
	public void setProducers(String producers) {
		this.producers = producers;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", releaseYear=" + releaseYear + ", title=" + title + ", studios=" + studios + ", producers="
				+ producers + ", winner=" + winner + "]";
	}
	
}
