package com.samuk159.worstmovie.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samuk159.worstmovie.model.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	public Optional<Producer> findByName(String name);
	public List<Producer> findByMovies_WinnerTrue();
	
	@Query("select p from Producer p "
			+ "inner join p.movies m "
			+ "where m.winner = true "
			+ "group by p.id "
			+ "having count(m.id) > 1"
	)
	public List<Producer> findWinnersWithAtLeastTwoMovies();
	
}
