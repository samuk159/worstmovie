package com.samuk159.worstmovie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

import com.samuk159.worstmovie.controller.CustomPageImpl;
import com.samuk159.worstmovie.controller.CustomResponse;
import com.samuk159.worstmovie.model.entity.Producer;
import com.samuk159.worstmovie.model.entity.Studio;

public class ProducerTest extends AbstractTest<Producer> {

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

}
