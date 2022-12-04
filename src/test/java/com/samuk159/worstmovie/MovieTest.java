package com.samuk159.worstmovie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;

import com.samuk159.worstmovie.controller.CustomPageImpl;
import com.samuk159.worstmovie.controller.CustomResponse;
import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.entity.Producer;
import com.samuk159.worstmovie.model.entity.Studio;
import com.samuk159.worstmovie.model.service.ProducerService;
import com.samuk159.worstmovie.model.service.StudioService;

public class MovieTest extends AbstractTest<Movie> {

	@Autowired
	private StudioService studioService;
	
	@Autowired
	private ProducerService producerService;
	
	@Override
	public String getUrl() {
		return "movies";
	}

	@Override
	public List<Movie> getInvalidEntities(Movie currentEntity) {
		Long id = currentEntity != null ? currentEntity.getId() : null;
		
		return new LinkedList<Movie>() {{
			add(new Movie(id, null, null, null, null, false));
			add(new Movie(id, null, "", null, null, false));
			add(new Movie(id, null, "test", null, null, false));
			add(new Movie(id, -1, "test", null, null, false));
			add(new Movie(id, 2022, "test", null, null, false));
			add(new Movie(id, 2022, "test", "", null, false));
			add(new Movie(id, 2022, "test", "studio", null, false));
			add(new Movie(id, 2022, "test", "studio", "", false));
			add(new Movie(id, 2022, "test", "", "producer", false));
		}};
	}

	@Override
	public Movie getValidEntity(Movie currentEntity) {
		if (currentEntity == null) {
			currentEntity = new Movie();
		}
		
		currentEntity.setTitle("test");
		currentEntity.setWinner(false);
		currentEntity.setReleaseYear(2022);
		
		if (currentEntity.getStudios().isEmpty()) {
			Studio studio = studioService.findAll(PageRequest.of(0, 1)).getContent().get(0);
			currentEntity.getStudios().add(studio);
		}
		
		if (currentEntity.getProducers().isEmpty()) {
			Producer producer = producerService.findAll(PageRequest.of(0, 1)).getContent().get(0);
			currentEntity.getProducers().add(producer);
		}
		
		return currentEntity;
	}

	@Override
	public void validateSavedEntity(Movie sentEntity, Movie savedEntity) {
		assertEquals(savedEntity.getTitle(), sentEntity.getTitle());
		assertEquals(savedEntity.isWinner(), sentEntity.isWinner());
		assertEquals(savedEntity.getReleaseYear(), sentEntity.getReleaseYear());
		
		if (sentEntity.getId() == null) {
			assertEquals(savedEntity.getStudios(), sentEntity.getStudios());
			assertEquals(savedEntity.getProducers(), sentEntity.getProducers());
		}
	}

	@Override
	public ParameterizedTypeReference<CustomPageImpl<Movie>> getPageTypeReference() {
		return new ParameterizedTypeReference<CustomPageImpl<Movie>>() {};
	}

	@Override
	public ParameterizedTypeReference<Movie> getTypeReference() {
		return new ParameterizedTypeReference<Movie>() {};
	}

	@Override
	public ParameterizedTypeReference<CustomResponse<Movie>> getCustomResponseTypeReference() {
		return new ParameterizedTypeReference<CustomResponse<Movie>>() {};
	}

}
