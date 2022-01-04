package com.challenge.disney.app.service;

import java.util.List;
import java.util.Optional;

import com.challenge.disney.app.entity.Character;

public interface CharacterService {
	
	public Iterable<Character> findAll();
	
	public Optional<Character> findById(Long id);
	
	public Character save(Character character);
	
	public void deleteById(Long id);
	
	public Optional<Character> findByName(String name);
	
	public List<Character> findByAge(Integer age);
	
	public Iterable<Character> readAllDefined();
	
}
