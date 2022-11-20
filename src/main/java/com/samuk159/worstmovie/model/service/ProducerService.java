package com.samuk159.worstmovie.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samuk159.worstmovie.dto.PrizeIntervalDTO;
import com.samuk159.worstmovie.dto.PrizeIntervalRow;
import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.repository.MovieRepository;

@Component
public class ProducerService {

	@Autowired
	private MovieRepository movieRepository;
	
	public PrizeIntervalDTO getMinAndMaxPrizeIntervals() {
		List<Movie> winningMovies = movieRepository.findByWinnerTrueOrderByProducersAscReleaseYearAsc();
		
		Movie previous = null;
		PrizeIntervalRow min = null;
		PrizeIntervalRow max = null;
		
		for (Movie movie : winningMovies) {
			if (previous != null && movie.getProducers() != null && movie.getProducers().equals(previous.getProducers())) {
				PrizeIntervalRow current = new PrizeIntervalRow(movie.getProducers(), previous.getReleaseYear(), movie.getReleaseYear());
				
				if (min == null || current.getInterval() < min.getInterval()) {
					min = current;
				} 
				
				if (max == null || current.getInterval() > max.getInterval()) {
					max = current;
				}
			}
			
			previous = movie;
		}
		
		PrizeIntervalDTO result = new PrizeIntervalDTO();
		
		if (min != null) {
			result.getMin().add(min);
		}
		
		if (max != null) {
			result.getMax().add(max);
		}
		
		return result;
		
	}
	
}
