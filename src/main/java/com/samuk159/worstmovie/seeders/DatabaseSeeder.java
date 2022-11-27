package com.samuk159.worstmovie.seeders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.samuk159.worstmovie.model.entity.Movie;
import com.samuk159.worstmovie.model.repository.MovieRepository;
import com.samuk159.worstmovie.model.service.MovieService;

@Component
public class DatabaseSeeder {
	
	@Autowired
	private MovieService movieService;

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		try {
			System.out.println("Starting seeder");
			seedMovies();
			//seedMockData();
		    System.out.println("Seed finished");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Seed error");
			System.exit(1);
		}
	}
	
	private void seedMockData() {
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

	private void seedMovies() throws FileNotFoundException, IOException {
		File file = ResourceUtils.getFile("classpath:movielist.csv");
        
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    boolean first = true;
		    while ((line = br.readLine()) != null) {		       
		       if (!first) {
		    	   String[] split = line.split(";");
		    	   boolean winner = split.length >= 5 && "yes".equalsIgnoreCase(split[4]);
		    	   Movie movie = new Movie(
                       Integer.parseInt(split[0]), 
                       split[1], 
                       split[2], 
                       split[3], 
                       winner
                   );
                   
		    	   movieService.save(movie);
		       }
		       
		       first = false;
		    }
		}
	}
	
}
