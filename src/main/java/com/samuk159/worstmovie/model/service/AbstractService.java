package com.samuk159.worstmovie.model.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class AbstractService<T> {

	protected abstract PagingAndSortingRepository<T, Long> getRepository();
	
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}
	
	public Optional<T> findById(Long id) {
		return getRepository().findById(id);
	}
	
	public T save(T entity) {
		return getRepository().save(entity);
	}
	
	public void deleteById(Long id) {
		getRepository().deleteById(id);
	}
	
}
