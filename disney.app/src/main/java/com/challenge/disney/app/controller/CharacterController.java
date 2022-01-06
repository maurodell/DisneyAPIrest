package com.challenge.disney.app.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.challenge.disney.app.entity.Character;
import com.challenge.disney.app.entity.Movie;
import com.challenge.disney.app.service.CharacterService;

@RestController
@RequestMapping("/character")
public class CharacterController {
	
	@Autowired
	private CharacterService characterService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Character character){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(characterService.save(character));
	}
	
	public static final String CHARACTER_UPLOADED_FOLDER = "images/character/";
	
	@PostMapping("/image")
	public ResponseEntity<byte[]> uploadImage(@RequestParam("id_character")Long idCharacter, @RequestParam("file") MultipartFile multipartFile, 
			UriComponentsBuilder componentsBuilder){
		if(idCharacter == null) {
			return ResponseEntity.notFound().build();
		}
		if(multipartFile.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Character character = characterService.findById(idCharacter).get();
		if(character == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(character.getImg() != null && !character.getImg().isEmpty()) {
			String fileName = character.getImg();
			Path path = Paths.get(fileName);
			File f = path.toFile();
			if(f.exists()) {
				f.delete();
			}
		}
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateName = dateFormat.format(date);
			
			String fileName = String.valueOf(idCharacter) + "-pictureMovie-" + dateName + "." + multipartFile.getContentType().split("/")[1];
			character.setImg(CHARACTER_UPLOADED_FOLDER + fileName);
			
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(CHARACTER_UPLOADED_FOLDER + fileName);
			Files.write(path, bytes);
			
			characterService.save(character);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long idC){
		Optional<Character> character = characterService.findById(idC);
		
		if(!character.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(character);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Character characterDetails, @PathVariable(value = "id") Long idC){
		Optional<Character> character = characterService.findById(idC);
		if(!character.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		character.get().setName(characterDetails.getName());
		//character.get().setImg(characterDetails.getImg());
		character.get().setAge(characterDetails.getAge());
		character.get().setHistory(characterDetails.getHistory());
		character.get().setWeight(characterDetails.getWeight());
		character.get().setMovies(characterDetails.getMovies());
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(characterService.save(character.get()));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long idC){
		if(!characterService.findById(idC).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		characterService.deleteById(idC);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/all")
	public List<Character> readAll(){
		List<Character> characters = StreamSupport
				.stream(characterService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return characters;
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<?> findByName(@PathVariable(value = "name") String nameC){
		Optional<Character> character = characterService.findByName(nameC);
		
		if(!character.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(character);
	}
	
	@GetMapping("/age/{age}")
	public List<Character> findByAge(@PathVariable(value = "age") Integer ageC){		
		List<Character> chatacter = StreamSupport
				.stream(characterService.findByAge(ageC).spliterator(), false)
				.collect(Collectors.toList());
		
		return chatacter;
	}
	
	@GetMapping
	public List<Character> readAllDefinded(){
		List<Character> characters = StreamSupport
				.stream(characterService.readAllDefined().spliterator(), false)
				.collect(Collectors.toList());
		return characters;
	}
}
