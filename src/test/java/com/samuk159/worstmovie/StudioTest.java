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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.samuk159.worstmovie.controller.CustomPageImpl;
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
	public void findAllTest() {
		ResponseEntity<CustomPageImpl<Studio>> response = testRestTemplate
	            .exchange("/studios", HttpMethod.GET, null, new ParameterizedTypeReference<CustomPageImpl<Studio>>() {});
	    
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertFalse(response.getBody().getContent().isEmpty());
	}

}
