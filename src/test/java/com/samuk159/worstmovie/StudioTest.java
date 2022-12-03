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
import com.samuk159.worstmovie.model.entity.Studio;
import com.samuk159.worstmovie.model.service.StudioService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class StudioTest {
	
	@Autowired
	private StudioService studioService;
	
	@Autowired
    private TestRestTemplate testRestTemplate;
	
	@Test
	public CustomPageImpl<Studio> findAllTest() {
		ResponseEntity<CustomPageImpl<Studio>> response = testRestTemplate
	            .exchange("/studios", HttpMethod.GET, null, new ParameterizedTypeReference<CustomPageImpl<Studio>>() {});
	    
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertFalse(response.getBody().getContent().isEmpty());
		return response.getBody();
	}
	
	@Test
	public Studio findByIdTest() {
		CustomPageImpl<Studio> all = findAllTest();
		Studio first = all.getContent().get(0);
		return findByIdTest(first);
	}
	
	public Studio findByIdTest(Studio studio) {
		ResponseEntity<Studio> response = testRestTemplate
	            .exchange("/studios/" + studio.getId(), HttpMethod.GET, null, Studio.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), studio);
		return response.getBody();
	}
	
	@Test
	public void createTest() {
		Studio studio = new Studio();
		ResponseEntity<CustomResponse<Studio>> response = createTest(studio);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);	
		
		studio.setName("");		
		response = createTest(studio);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);	
		
		studio.setName("test");		
		response = createTest(studio);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		
		Studio createdStudio = response.getBody().getData();
		assertNotNull(createdStudio);
		assertEquals(createdStudio.getName(), studio.getName());
		findByIdTest(createdStudio);
	}
	
	public ResponseEntity<CustomResponse<Studio>> createTest(Studio studio) {
		HttpEntity<Studio> httpEntity = new HttpEntity<>(studio);		
		return testRestTemplate
	            .exchange("/studios", HttpMethod.POST, httpEntity, new ParameterizedTypeReference<CustomResponse<Studio>>() {});
	}
	
	@Test
	public void updateTest() {
		Studio studio = findByIdTest();
		studio.setName(null);
		
		ResponseEntity<Studio> response = updateTest(studio);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);	
		
		studio.setName("");		
		response = updateTest(studio);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);	
		
		studio.setName("test");		
		response = updateTest(studio);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
		Studio createdStudio = response.getBody();
		assertNotNull(createdStudio);
		assertEquals(createdStudio.getName(), studio.getName());
		assertEquals(createdStudio.getId(), studio.getId());
	}
	
	public ResponseEntity<Studio> updateTest(Studio studio) {
		HttpEntity<Studio> httpEntity = new HttpEntity<>(studio);		
		return testRestTemplate
	            .exchange("/studios/" + studio.getId(), HttpMethod.PUT, httpEntity, Studio.class);
	}
	
	@Test
	public void deleteTest() {
		Studio studio = new Studio("delete test");
		studio = createTest(studio).getBody().getData();
		ResponseEntity<Studio> response = testRestTemplate
	            .exchange("/studios/" + studio.getId(), HttpMethod.DELETE, null, Studio.class);
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
		
		response = testRestTemplate
	            .exchange("/studios/" + studio.getId(), HttpMethod.GET, null, Studio.class);
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

}
