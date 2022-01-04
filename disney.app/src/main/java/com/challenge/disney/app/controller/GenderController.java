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

import com.challenge.disney.app.entity.Gender;
import com.challenge.disney.app.service.GenderService;

@RestController
@RequestMapping("/v4/genders")
public class GenderController {
	
	@Autowired
	private GenderService genderService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Gender gender){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(genderService.save(gender));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long idG){
		
		Optional<Gender> gender = genderService.findById(idG);
		
		if(!gender.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(gender);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Gender genderDetails, @PathVariable(value = "id") Long idG){
		Optional<Gender> gender = genderService.findById(idG);
		if(!gender.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		gender.get().setImg(genderDetails.getImg());
		gender.get().setName(genderDetails.getName());
		gender.get().setMovies(genderDetails.getMovies());
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(genderService.save(gender.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long idG){
		if(!genderService.findById(idG).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		genderService.deletedById(idG);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Gender> readAll(){
		List<Gender> gender = StreamSupport
				.stream(genderService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return gender;
	}
}
