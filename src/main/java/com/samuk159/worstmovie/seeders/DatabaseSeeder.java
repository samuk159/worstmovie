package com.samuk159.worstmovie.seeders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.samuk159.worstmovie.model.entity.Movie;

@Component
public class DatabaseSeeder {

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		try {
			System.out.println("Starting seeder");
			seedMovies();
		    System.out.println("Seed finished");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Seed error");
			System.exit(1);
		}
	}
	
	private void seedMovies() throws CsvValidationException, FileNotFoundException, IOException {
		List<Movie> movies = readCSV();
	}
	
	private List<Movie> readCSV() throws FileNotFoundException, IOException, CsvValidationException {
		List<Movie> movies = new ArrayList<>();
		File file = ResourceUtils.getFile("classpath:movielist.csv");
        
		try (
			Reader reader = new FileReader(file); 
			CSVReader csvReader = new CSVReader(reader, ';');
		) {
			csvReader.skip(1);
			//TODO ler usando ; como separador
			
		    String[] values = null;
		    long id = 1;
		    
		    while ((values = csvReader.readNext()) != null) {
		    	Movie movie = new Movie(
	    			id, 
	    			Integer.parseInt(values[0]), 
	    			values[1], 
	    			values[2], 
	    			values[3], 
	    			"yes".equalsIgnoreCase(values[4])
				);
		    	System.out.println(movie);
		    	movies.add(movie);
		        id++;
		    }
		}
		
		return movies;
	}
	
}
