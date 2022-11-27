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
		//TODO selecionar somente producers com mais de um filme (habing count)
		//TODO resultado parece incorreto
		List<Producer> producers = producerRepository.findByMovies_WinnerTrue();
		//List<Producer> producers = producerRepository.findAll();
		System.out.println(producers.size());
		PrizeIntervalDTO result = new PrizeIntervalDTO();		
		Integer minInterval = null;
		Integer maxInterval = null;
		
		for (Producer producer : producers) {			
			for (int i = 0; i < producer.getMovies().size(); i++) {				
				for (int j = i + 1; j < producer.getMovies().size(); j++) {
					Movie m1 = producer.getMovies().get(i);
					Movie m2 = producer.getMovies().get(j);
					
					System.out.println(m1.isWinner());
					System.out.println(m2.isWinner());
					
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
