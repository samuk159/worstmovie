package com.samuk159.worstmovie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samuk159.worstmovie.controller.CustomPageImpl;
import com.samuk159.worstmovie.controller.CustomResponse;
import com.samuk159.worstmovie.dto.PrizeIntervalDTO;
import com.samuk159.worstmovie.dto.PrizeIntervalRow;
import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.entity.Producer;
import com.samuk159.worstmovie.model.entity.Studio;
import com.samuk159.worstmovie.model.repository.MovieRepository;
import com.samuk159.worstmovie.model.repository.ProducerRepository;
import com.samuk159.worstmovie.model.repository.StudioRepository;
import com.samuk159.worstmovie.model.service.MovieService;

public class ProducerTest extends AbstractTest<Producer> {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private StudioRepository studioRepository;
	
	@Autowired
	private ProducerRepository producerRepository;

	@Override
	public String getUrl() {
		return "producers";
	}

	@Override
	public List<Producer> getInvalidEntities(Producer currentEntity) {
		Long id = currentEntity != null ? currentEntity.getId() : null;
		
		return new LinkedList<Producer>() {{
			add(new Producer(id, null));
			add(new Producer(id, ""));
		}};
	}

	@Override
	public Producer getValidEntity(Producer currentEntity) {
		if (currentEntity == null) {
			currentEntity = new Producer();
		}
		
		currentEntity.setName("test");
		return currentEntity;
	}

	@Override
	public void validateSavedEntity(Producer sentEntity, Producer savedEntity) {
		assertEquals(savedEntity.getName(), sentEntity.getName());
	}

	@Override
	public ParameterizedTypeReference<CustomPageImpl<Producer>> getPageTypeReference() {
		return new ParameterizedTypeReference<CustomPageImpl<Producer>>() {};
	}

	@Override
	public ParameterizedTypeReference<Producer> getTypeReference() {
		return new ParameterizedTypeReference<Producer>() {};
	}

	@Override
	public ParameterizedTypeReference<CustomResponse<Producer>> getCustomResponseTypeReference() {
		return new ParameterizedTypeReference<CustomResponse<Producer>>() {};
	}
	
	@Test
	public void getMinAndMaxPrizeIntervalsTest() {		
		seedMockData();
		
		ResponseEntity<PrizeIntervalDTO> response = testRestTemplate
	            .exchange("/" + getUrl() + "/prize-intervals", HttpMethod.GET, null, PrizeIntervalDTO.class);
	    
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		PrizeIntervalDTO dto = response.getBody();
		assertNotNull(dto);
		assertEquals(dto.getMin().size(), 1);
		assertEquals(dto.getMax().size(), 1);
		
		PrizeIntervalRow min = dto.getMin().get(0);
		assertEquals(min.getProducer(), "e");
		assertEquals(min.getPreviousWin(), 2002);
		assertEquals(min.getFollowingWin(), 2003);
		assertEquals(min.getInterval(), 1);
		
		PrizeIntervalRow max = dto.getMax().get(0);
		assertEquals(max.getProducer(), "g");
		assertEquals(max.getPreviousWin(), 2010);
		assertEquals(max.getFollowingWin(), 2050);
		assertEquals(max.getInterval(), 40);
	}
	
	private void seedMockData() {
		movieRepository.deleteAll();
		studioRepository.deleteAll();
		producerRepository.deleteAll();
		
		List<Movie> mockMovies = new LinkedList<Movie>() {{
			add(new Movie(2000, "a", false));
			add(new Movie(2000, "a", true));
			add(new Movie(2000, "b", true));
			add(new Movie(2010, "b", true));
			add(new Movie(2012, "b", true));
			add(new Movie(2000, "c", true));
			add(new Movie(2002, "c", true));
			add(new Movie(2022, "c", true));
			
			add(new Movie(2003, "a", true));
			add(new Movie(2022, "a", true));
			
			add(new Movie(2000, "d", true));
			add(new Movie(2002, "d, e", true));
			add(new Movie(2003, "e", true));
			
			add(new Movie(2000, "f", true));
			add(new Movie(2010, "f, g", true));
			add(new Movie(2050, "g", true));
		}};
		
		for (Movie movie : mockMovies) {
			this.movieService.save(movie);
		}
	}

}
