package com.challenge.disney.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.disney.app.repository.CharacterRepository;

import com.challenge.disney.app.entity.Character;

@Service
public class CharacterServiceImp implements CharacterService{
	
	@Autowired
	private CharacterRepository characterRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Character> findAll() {
		return characterRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Character> findById(Long id) {
		return characterRepository.findById(id);
	}
		
	@Override
	@Transactional
	public Character save(Character character) {
		return characterRepository.save(character);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		characterRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Character> findByName(String name) {
		return characterRepository.searchByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Character> findByAge(Integer age) {
		return characterRepository.searchByAge(age);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Character> readAllDefined() {
		return characterRepository.readAllDefined();
	}
	
	
}
