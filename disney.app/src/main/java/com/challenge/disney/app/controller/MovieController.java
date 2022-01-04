package com.challenge.disney.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.disney.app.entity.Movie;
import com.challenge.disney.app.service.MovieService;

@RestController
@RequestMapping("/v3/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Movie movie){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(movieService.save(movie));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long idM){
		Optional<Movie> movie = movieService.findById(idM);
		
		if(!movie.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(movie);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Movie movieDetails, @PathVariable(value="id") Long idM){
		Optional<Movie> movie = movieService.findById(idM);
		
		if(!movie.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		movie.get().setTitle(movieDetails.getTitle());
		movie.get().setCharacter(movieDetails.getCharacter());
		movie.get().setDate(movieDetails.getDate());
		movie.get().setGender(movieDetails.getGender());
		movie.get().setImg(movieDetails.getImg());
		movie.get().setQualification(movieDetails.getQualification());
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(movieService.save(movie.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long idM){
		if(!movieService.findById(idM).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		movieService.deleteById(idM);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Movie> readAll(){
		List<Movie> movies = StreamSupport
				.stream(movieService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return movies;
	}
}
