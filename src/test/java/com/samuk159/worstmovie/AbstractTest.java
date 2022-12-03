package com.samuk159.worstmovie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.samuk159.worstmovie.controller.CustomPageImpl;
import com.samuk159.worstmovie.controller.CustomResponse;
import com.samuk159.worstmovie.model.entity.AbstractEntity;
import com.samuk159.worstmovie.model.entity.Studio;
import com.samuk159.worstmovie.model.service.StudioService;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public abstract class AbstractTest<T extends AbstractEntity> {
	
	@Autowired
    private TestRestTemplate testRestTemplate;
	
	public abstract String getUrl();
	public abstract ParameterizedTypeReference<CustomPageImpl<T>> getPageTypeReference();
	public abstract ParameterizedTypeReference<T> getTypeReference();
	public abstract ParameterizedTypeReference<CustomResponse<T>> getCustomResponseTypeReference();
	
	@Test
	public void findAllTest() {
		findAllTest(false);
	}
	
	public CustomPageImpl<T> findAllTest(boolean checkSize) {
		ResponseEntity<CustomPageImpl<T>> response = testRestTemplate
	            .exchange("/" + getUrl(), HttpMethod.GET, null, getPageTypeReference());
	    
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
		if (checkSize) {
			assertFalse(response.getBody().getContent().isEmpty());
		}
		
		return response.getBody();
	}
	
	@Test
	public T findByIdTest() {
		CustomPageImpl<T> all = findAllTest(true);
		T first = all.getContent().get(0);
		return findByIdTest(first);
	}
	
	public T findByIdTest(T studio) {
		ResponseEntity<T> response = testRestTemplate
	            .exchange("/" + getUrl() + "/" + studio.getId(), HttpMethod.GET, null, getTypeReference());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), studio);
		return response.getBody();
	}
	
	public abstract List<T> getInvalidEntities(T currentEntity);
	public abstract T getValidEntity(T currentEntity);
	public abstract void validateSavedEntity(T sentEntity, T savedEntity);
	
	@Test
	public void createTest() {
		List<T> invalidEntites = getInvalidEntities(null);
		
		for (T e : invalidEntites) {
			ResponseEntity<CustomResponse<T>> response = createTest(e);
			assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);	
		}
		
		T entity = getValidEntity(null);		
		ResponseEntity<CustomResponse<T>> response = createTest(entity);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		
		T created = response.getBody().getData();
		assertNotNull(created);
		validateSavedEntity(entity, created);
		findByIdTest(created);
		findAllTest(true);
	}
	
	public ResponseEntity<CustomResponse<T>> createTest(T studio) {
		HttpEntity<T> httpEntity = new HttpEntity<>(studio);		
		return testRestTemplate
	            .exchange("/" + getUrl(), HttpMethod.POST, httpEntity, getCustomResponseTypeReference());
	}
	
	@Test
	public void updateTest() {
		T entity = findByIdTest();
		
		List<T> invalidEntites = getInvalidEntities(entity);
		
		for (T e : invalidEntites) {
			ResponseEntity<T> response = updateTest(e);
			assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);	
		}
		
		entity = getValidEntity(entity);	
		ResponseEntity<T> response = updateTest(entity);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
		T updated = response.getBody();
		assertNotNull(updated);
		validateSavedEntity(entity, updated);
		assertEquals(updated.getId(), updated.getId());
	}
	
	public ResponseEntity<T> updateTest(T studio) {
		HttpEntity<T> httpEntity = new HttpEntity<>(studio);		
		return testRestTemplate
	            .exchange("/" + getUrl() + "/" + studio.getId(), HttpMethod.PUT, httpEntity, getTypeReference());
	}
	
	public abstract T getDeleteTestEntity();
	
	@Test
	public void deleteTest() {
		T studio = getDeleteTestEntity();
		studio = createTest(studio).getBody().getData();
		ResponseEntity<T> response = testRestTemplate
	            .exchange("/" + getUrl() + "/" + studio.getId(), HttpMethod.DELETE, null, getTypeReference());
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
		
		response = testRestTemplate
	            .exchange("/" + getUrl() + "/" + studio.getId(), HttpMethod.GET, null, getTypeReference());
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

}
