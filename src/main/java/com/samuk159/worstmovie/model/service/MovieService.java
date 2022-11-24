package com.samuk159.worstmovie.model.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.entity.Producer;
import com.samuk159.worstmovie.model.entity.Studio;
import com.samuk159.worstmovie.model.repository.MovieRepository;
import com.samuk159.worstmovie.model.repository.ProducerRepository;
import com.samuk159.worstmovie.model.repository.StudioRepository;

@Component
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private StudioRepository studioRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	public Page<Movie> findAll(Pageable pageable) {
		return movieRepository.findAll(pageable);
	}
	
	public Optional<Movie> findById(Long id) {
		return movieRepository.findById(id);
	}
	
	public Movie save(Movie movie) {
		List<Studio> newStudios = new LinkedList<>();
		
		for (Studio studio : movie.getStudios()) {
			Optional<Studio> opt = studioRepository.findByName(studio.getName());
			
			if (opt.isPresent()) {
				newStudios.add(opt.get());
			} else {
				newStudios.add(studioRepository.save(studio));
			}
		}
		
		movie.setStudios(newStudios);
		
		List<Producer> newProducers = new LinkedList<>();
		
		for (Producer producer : movie.getProducers()) {
			Optional<Producer> opt = producerRepository.findByName(producer.getName());
			
			if (opt.isPresent()) {
				newProducers.add(opt.get());
			} else {
				newProducers.add(producerRepository.save(producer));
			}
		}
		
		movie.setProducers(newProducers);
		
		return movieRepository.save(movie);
	}
	
	public void deleteById(Long id) {
		movieRepository.deleteById(id);
	}
	
}
