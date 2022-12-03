package com.samuk159.worstmovie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

import com.samuk159.worstmovie.controller.CustomPageImpl;
import com.samuk159.worstmovie.controller.CustomResponse;
import com.samuk159.worstmovie.model.entity.Studio;

public class StudioTest extends AbstractTest<Studio> {

	@Override
	public String getUrl() {
		return "studios";
	}

	@Override
	public List<Studio> getInvalidEntities(Studio currentEntity) {
		Long id = currentEntity != null ? currentEntity.getId() : null;
		
		return new LinkedList<Studio>() {{
			add(new Studio(id, null));
			add(new Studio(id, ""));
		}};
	}

	@Override
	public Studio getValidEntity(Studio currentEntity) {
		if (currentEntity == null) {
			currentEntity = new Studio();
		}
		
		currentEntity.setName("test");
		return currentEntity;
	}

	@Override
	public void validateSavedEntity(Studio sentEntity, Studio savedEntity) {
		assertEquals(savedEntity.getName(), sentEntity.getName());
	}

	@Override
	public ParameterizedTypeReference<CustomPageImpl<Studio>> getPageTypeReference() {
		return new ParameterizedTypeReference<CustomPageImpl<Studio>>() {};
	}

	@Override
	public ParameterizedTypeReference<Studio> getTypeReference() {
		return new ParameterizedTypeReference<Studio>() {};
	}

	@Override
	public ParameterizedTypeReference<CustomResponse<Studio>> getCustomResponseTypeReference() {
		return new ParameterizedTypeReference<CustomResponse<Studio>>() {};
	}

}
