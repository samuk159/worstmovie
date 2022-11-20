package com.samuk159.worstmovie.model.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		Map<String, List<Movie>> producersMovies = groupMoviesByProducer(winningMovies); 
		
		PrizeIntervalRow min = null;
		PrizeIntervalRow max = null;
		
		for (Entry<String, List<Movie>> entry : producersMovies.entrySet()) {
			Movie previous = null;
			
			for (int i = 0; i < entry.getValue().size(); i++) {				
				for (int j = i + 1; j < entry.getValue().size(); j++) {
					Movie m1 = entry.getValue().get(i);
					Movie m2 = entry.getValue().get(j);
					PrizeIntervalRow current = new PrizeIntervalRow(entry.getKey(), m1.getReleaseYear(), m2.getReleaseYear());
					
					if (min == null || current.getInterval() < min.getInterval()) {
						min = current;
					} 
					
					if (max == null || current.getInterval() > max.getInterval()) {
						max = current;
					}
				}
			}
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

	private Map<String, List<Movie>> groupMoviesByProducer(List<Movie> movies) {
		Map<String, List<Movie>> result = new HashMap<>();
		
		for (Movie movie : movies) {
			String producer = movie.getProducers();
			
			if (producer != null) {
				if (result.get(producer) == null) {
					result.put(producer, new LinkedList<Movie>());
				}
				
				result.get(producer).add(movie);
			}
		}
		
		return result;
	}
	
}
