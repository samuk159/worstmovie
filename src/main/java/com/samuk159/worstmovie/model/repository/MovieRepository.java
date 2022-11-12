package com.samuk159.worstmovie.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuk159.worstmovie.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
