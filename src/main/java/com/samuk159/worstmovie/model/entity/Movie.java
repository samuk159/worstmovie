package com.samuk159.worstmovie.model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.samuk159.worstmovie.util.StringUtils;

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
	
	@NotEmpty
	@OneToMany(mappedBy = "movie")
	private List<Studio> studios;
	
	@NotEmpty
	@OneToMany(mappedBy = "movie")
	private List<Producer> producers;
	
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
		this.studios = StringUtils.splitByComma(studios).stream().map(s -> new Studio(s)).collect(Collectors.toList());
		this.producers = StringUtils.splitByComma(producers).stream().map(s -> new Producer(s)).collect(Collectors.toList());
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
	public List<Studio> getStudios() {
		if (studios == null) {
			studios = new LinkedList<Studio>();
		}
		
		return studios;
	}

	public void setStudios(List<Studio> studios) {
		this.studios = studios;
	}

	public List<Producer> getProducers() {
		if (producers == null) {
			producers = new LinkedList<Producer>();
		}
		
		return producers;
	}

	public void setProducers(List<Producer> producers) {
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
