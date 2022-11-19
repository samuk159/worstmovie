package com.samuk159.worstmovie.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.samuk159.worstmovie.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>, PagingAndSortingRepository<Movie, Long> {

	/*@Query("select "
			+ "m.releaseYear,"
			+ "m.title,"
			+ "LAG(releaseYear) AS previous_year, " + 
			"  releaseYear - LAG(releaseYear) " + 
			"    OVER (ORDER BY releaseYear ) AS yearDiff "
			+ "from Movie m "
			+ "where m.winner = true "
			+ "and m.producers = 'Bo Derek'"
			//+ "group by m.producers"
	)*/
	@Query(
			"select "
			//+ "m1, m2 "
			+ "m1.producers, "
			+ "min(m2.releaseYear - m1.releaseYear) as minDiff, "
			+ "max(m2.releaseYear - m1.releaseYear) as maxDiff "
			+ "from Movie m1 "
			+ "inner join Movie m2 on m1.producers = m2.producers and m1.id != m2.id and m2.winner = true and m2.releaseYear >= m1.releaseYear "
			+ "where m1.winner = true "
			+ "group by m1.producers"
	)
	public List<Object[]> getMinAndMaxIntervalProducers();
	
}
