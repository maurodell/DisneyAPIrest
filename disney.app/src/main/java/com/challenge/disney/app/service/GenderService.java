package com.challenge.disney.app.service;

import java.util.Optional;

import com.challenge.disney.app.entity.Gender;

public interface GenderService {
	
	public Iterable<Gender> findAll();
	
	public Optional<Gender> findById(Long id);
	
	public Gender save(Gender gender);
	
	public void deletedById(Long id);
}
