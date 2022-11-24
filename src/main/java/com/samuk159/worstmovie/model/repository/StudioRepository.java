package com.samuk159.worstmovie.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuk159.worstmovie.model.entity.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long> {

	public Optional<Studio> findByName(String name);
	
}
