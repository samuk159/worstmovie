package com.samuk159.worstmovie.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.repository.MovieRepository;
import com.samuk159.worstmovie.model.service.AbstractService;
import com.samuk159.worstmovie.model.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController extends AbstractController<Movie> {
	
	@Autowired
	private MovieService service;

	@Override
	protected AbstractService<Movie> getService() {
		return service;
	}
	
}
