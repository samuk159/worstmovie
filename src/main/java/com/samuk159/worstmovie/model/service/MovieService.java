package com.samuk159.worstmovie.model.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.entity.Producer;
import com.samuk159.worstmovie.model.entity.Studio;
import com.samuk159.worstmovie.model.repository.MovieRepository;
import com.samuk159.worstmovie.model.repository.ProducerRepository;
import com.samuk159.worstmovie.model.repository.StudioRepository;

@Component
public class MovieService extends AbstractService<Movie> {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private StudioService studioService;
	
	@Autowired
	private ProducerService producerService;
	
	public Movie save(Movie movie) {		
		List<Studio> newStudios = new LinkedList<>();
		
		for (Studio studio : movie.getStudios()) {
			Optional<Studio> opt = studioService.findByName(studio.getName());
			
			if (opt.isPresent()) {
				newStudios.add(opt.get());
			} else {
				studio = studioService.save(studio);
				newStudios.add(studio);
			}
		}
		
		movie.setStudios(newStudios);
		
		List<Producer> newProducers = new LinkedList<>();
		
		for (Producer producer : movie.getProducers()) {
			Optional<Producer> opt = producerService.findByName(producer.getName());
			
			if (opt.isPresent()) {
				newProducers.add(opt.get());
			} else {
				producer = producerService.save(producer);
				newProducers.add(producer);
			}
		}
		
		movie.setProducers(newProducers);
		
		return super.save(movie);
	}

	@Override
	protected PagingAndSortingRepository<Movie, Long> getRepository() {
		return movieRepository;
	}
	
}
