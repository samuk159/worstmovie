package com.samuk159.worstmovie.model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samuk159.worstmovie.util.StringUtils;

@Entity
public class Movie extends AbstractEntity {
	
	@NotNull
	@Min(0)
	private Integer releaseYear;
	
	@NotBlank
	private String title;
	
	@Valid
	@JsonIgnoreProperties("movies")
	@NotEmpty
	@ManyToMany
	private List<Studio> studios;
	
	@Valid
	@JsonIgnoreProperties("movies")
	@NotEmpty
	@ManyToMany
	private List<Producer> producers;
	
	private boolean winner = false;
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Movie(Integer releaseYear, String title, String studios, String producers, boolean winner) {
		this.releaseYear = releaseYear;
		this.title = title;
		
		if (studios != null) {
			this.studios = StringUtils.splitByComma(studios).stream().map(s -> new Studio(s)).collect(Collectors.toList());
		}
		
		if (producers != null) {
			this.producers = StringUtils.splitByComma(producers).stream().map(s -> new Producer(s)).collect(Collectors.toList());
		}
		
		this.winner = winner;
	}
	
	public Movie(Long id, Integer releaseYear, String title, String studios, String producers, boolean winner) {
		this(releaseYear, title, studios, producers, winner);
		this.setId(id);
	}
	
	public Movie(Integer releaseYear, String producers, boolean winner) {
		this(releaseYear, producers, producers, producers, winner);
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
		return "Movie [id=" + getId() + ", releaseYear=" + releaseYear + ", title=" + title + ", winner=" + winner + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((producers == null) ? 0 : producers.hashCode());
		result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
		result = prime * result + ((studios == null) ? 0 : studios.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + (winner ? 1231 : 1237);
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
		Movie other = (Movie) obj;
		if (producers == null) {
			if (other.producers != null)
				return false;
		} else if (!producers.equals(other.producers))
			return false;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		if (studios == null) {
			if (other.studios != null)
				return false;
		} else if (!studios.equals(other.studios))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (winner != other.winner)
			return false;
		return true;
	}
	
}
