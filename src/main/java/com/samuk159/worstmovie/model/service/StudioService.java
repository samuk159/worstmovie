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
public class StudioService extends AbstractService<Studio> {
	
	@Autowired
	private StudioRepository studioRepository;

	@Override
	protected PagingAndSortingRepository<Studio, Long> getRepository() {
		return studioRepository;
	}
	
	public Optional<Studio> findByName(String name) {
		return studioRepository.findByName(name);
	}
	
}
