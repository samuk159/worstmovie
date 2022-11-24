package com.samuk159.worstmovie.model.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.samuk159.worstmovie.dto.PrizeIntervalDTO;
import com.samuk159.worstmovie.dto.PrizeIntervalRow;
import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.entity.Producer;
import com.samuk159.worstmovie.model.repository.MovieRepository;
import com.samuk159.worstmovie.model.repository.ProducerRepository;

@Component
public class ProducerService extends AbstractService<Producer> {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	public PrizeIntervalDTO getMinAndMaxPrizeIntervals() {
		//List<Movie> winningMovies = movieRepository.findByWinnerTrueOrderByProducersAscReleaseYearAsc();
		//Map<String, List<Movie>> producersMovies = groupMoviesByProducer(winningMovies); 
		PrizeIntervalDTO result = new PrizeIntervalDTO();		
		/*Integer minInterval = null;
		Integer maxInterval = null;
		
		for (Entry<String, List<Movie>> entry : producersMovies.entrySet()) {			
			for (int i = 0; i < entry.getValue().size(); i++) {				
				for (int j = i + 1; j < entry.getValue().size(); j++) {
					Movie m1 = entry.getValue().get(i);
					Movie m2 = entry.getValue().get(j);
					PrizeIntervalRow current = new PrizeIntervalRow(entry.getKey(), m1.getReleaseYear(), m2.getReleaseYear());
					
					if (minInterval == null || current.getInterval() <= minInterval) {
						if (minInterval != null && current.getInterval() < minInterval) {
							result.getMin().clear();
						}
						
						minInterval = current.getInterval();						
						result.getMin().add(current);
					} 
					
					if (maxInterval == null || current.getInterval() >= maxInterval) {
						if (maxInterval != null && current.getInterval() > maxInterval) {
							result.getMax().clear();
						}
						
						maxInterval = current.getInterval();						
						result.getMax().add(current);
					}
				}
			}
		}*/
		
		return result;
		
	}

	@Override
	protected PagingAndSortingRepository<Producer, Long> getRepository() {
		return producerRepository;
	}

	/*private Map<String, List<Movie>> groupMoviesByProducer(List<Movie> movies) {
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
	}*/
	
}
