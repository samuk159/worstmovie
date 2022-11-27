package com.samuk159.worstmovie.model.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
	private ProducerRepository producerRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Optional<Producer> findByName(String name) {
		return producerRepository.findByName(name);
	}
	
	public PrizeIntervalDTO getMinAndMaxPrizeIntervals() {
		List<Producer> producers = producerRepository.findWinnersWithAtLeastTwoMovies();
		System.out.println("Producers: " + producers.size());
		PrizeIntervalDTO result = new PrizeIntervalDTO();		
		Integer minInterval = null;
		Integer maxInterval = null;
		
		for (Producer producer : producers) {		
			List<Movie> movies = movieRepository.findByProducersAndWinnerTrue(producer);
			System.out.println(producer.getName() + " - " + movies.size());
			
			for (int i = 0; i < movies.size(); i++) {				
				for (int j = i + 1; j < movies.size(); j++) {
					Movie m1 = movies.get(i);
					Movie m2 = movies.get(j);
					
					PrizeIntervalRow current = new PrizeIntervalRow(producer.getName(), m1.getReleaseYear(), m2.getReleaseYear());
					
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
		}
		
		return result;
		
	}

	@Override
	protected PagingAndSortingRepository<Producer, Long> getRepository() {
		return producerRepository;
	}
	
}
