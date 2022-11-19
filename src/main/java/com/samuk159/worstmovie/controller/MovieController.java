package com.samuk159.worstmovie.controller;

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

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieRepository repository;
	
	@GetMapping
	public Page<Movie> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie> findById(@PathVariable Long id) {
		Optional<Movie> opt = repository.findById(id);
		if (opt.isPresent()) {
			return ResponseEntity.ok(opt.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<CustomResponse<Movie>> create(@Valid @RequestBody Movie movie) {
		CustomResponse<Movie> res = new CustomResponse<Movie>();
		
		if (movie.getId() != null) {
			res.setMessage("Id não pode ser informado");
			return ResponseEntity.badRequest().body(res);
		}
		
		res.setData(repository.save(movie));
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Movie> update(@PathVariable Long id, @Valid @RequestBody Movie movie) {
		Optional<Movie> opt = repository.findById(id);
		
		if (opt.isPresent()) {
			movie.setId(id);
			return ResponseEntity.ok(repository.save(movie));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Movie> delete(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
