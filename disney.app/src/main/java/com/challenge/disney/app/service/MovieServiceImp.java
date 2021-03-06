package com.challenge.disney.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.challenge.disney.app.entity.Movie;
import com.challenge.disney.app.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Movie> findById(Long id) {
		return movieRepository.findById(id);
	}

	@Override
	@Transactional
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		movieRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Movie> readAllDefined() {
		return movieRepository.readAllDefined();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Movie> searchByTitle(String title) {
		return movieRepository.searchByTitle(title);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Movie> readGender(String gender) {
		return movieRepository.readGender(gender);
	}

	@Override
	public Iterable<Movie> findAllOrderAsc() {
		return movieRepository.readAllOrderAsc();
	}

	@Override
	public Iterable<Movie> findAllOrderDesc() {
		return movieRepository.readAllOrderDesc();
	}

}
