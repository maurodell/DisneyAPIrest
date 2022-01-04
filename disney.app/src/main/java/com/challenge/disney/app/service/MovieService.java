package com.challenge.disney.app.service;

import java.util.Optional;

import com.challenge.disney.app.entity.Movie;

public interface MovieService {
	
	public Iterable<Movie> findAll();
	
	public Optional<Movie> findById(Long id);
	
	public Movie save(Movie movie);
	
	public void deleteById(Long id);
}
