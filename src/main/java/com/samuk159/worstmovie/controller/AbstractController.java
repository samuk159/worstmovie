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

import com.samuk159.worstmovie.model.entity.AbstractEntity;
import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.repository.MovieRepository;
import com.samuk159.worstmovie.model.service.AbstractService;
import com.samuk159.worstmovie.model.service.MovieService;

public abstract class AbstractController<T extends AbstractEntity> {
	
	protected abstract AbstractService<T> getService();
	
	@GetMapping
	public Page<T> findAll(Pageable pageable) {
		return getService().findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<T> findById(@PathVariable Long id) {
		Optional<T> opt = getService().findById(id);
		if (opt.isPresent()) {
			return ResponseEntity.ok(opt.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<CustomResponse<T>> create(@Valid @RequestBody T entity) {
		CustomResponse<T> res = new CustomResponse<T>();		
		res.setData(getService().save(entity));
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<T> update(@PathVariable Long id, @Valid @RequestBody T entity) {
		Optional<T> opt = getService().findById(id);
		
		if (opt.isPresent()) {
			entity.setId(id);
			return ResponseEntity.ok(getService().save(entity));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<T> delete(@PathVariable Long id) {
		getService().deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
