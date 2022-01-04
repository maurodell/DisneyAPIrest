package com.challenge.disney.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.disney.app.entity.Gender;
import com.challenge.disney.app.repository.GenderRepository;

@Service
public class GenderServiceImp implements GenderService {
	
	private GenderRepository genderRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Gender> findAll() {
		return genderRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Gender> findById(Long id) {
		return genderRepository.findById(id);
	}

	@Override
	@Transactional
	public Gender save(Gender gender) {
		return genderRepository.save(gender);
	}

	@Override
	@Transactional
	public void deletedById(Long id) {
		genderRepository.deleteById(id);
	}

}
