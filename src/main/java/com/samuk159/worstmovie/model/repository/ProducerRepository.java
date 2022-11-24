package com.samuk159.worstmovie.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuk159.worstmovie.model.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	public Optional<Producer> findByName(String name);
	
}
