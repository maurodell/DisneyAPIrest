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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.challenge.disney.app.entity.Movie;
import com.challenge.disney.app.service.MovieService;

@RestController
@RequestMapping("/v3/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Movie movie){

		if(movie.getQualification() >= 1 && movie.getQualification() <= 5) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(movieService.save(movie));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	public static final String MOVIE_UPLOADED_FOLDER = "images/movie/";
	
	//@RequestMapping(value = "/image/{id_movie}", method = RequestMethod.POST, headers=("content-type=multipart/form-data"))
	@PostMapping("/image")
	public ResponseEntity<byte[]> uploadImage(@RequestParam("id_movie")Long idMovie, @RequestParam("file") MultipartFile multipartFile, 
			UriComponentsBuilder componentsBuilder){
		if(idMovie == null) {
			return ResponseEntity.notFound().build();
		}
		if(multipartFile.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Movie movie = movieService.findById(idMovie).get();
		if(movie == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(movie.getImg() != null || !movie.getImg().isEmpty()) {
			String fileName = movie.getImg();
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
			
			String fileName = String.valueOf(idMovie) + "-pictureMovie-" + dateName + "." + multipartFile.getContentType().split("/")[1];
			movie.setImg(MOVIE_UPLOADED_FOLDER + fileName);
			
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(MOVIE_UPLOADED_FOLDER + fileName);
			Files.write(path, bytes);
			
			movieService.save(movie);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
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
		//movie.get().setImg(movieDetails.getImg());
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
	
	@GetMapping("/all")
	public List<Movie> readAll(){
		List<Movie> movies = StreamSupport
				.stream(movieService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return movies;
	}
	
	@GetMapping
	public List<Movie> readAllDefined(){
		List<Movie> movies = StreamSupport
				.stream(movieService.readAllDefined().spliterator(), false)
				.collect(Collectors.toList());
		
		return movies;
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<?> findByName(@PathVariable(value = "name") String title){
		Optional<Movie> movie = movieService.searchByTitle(title);
		if(!movie.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(movie);
	}
	
	@GetMapping("/gender/{gender}")
	public List<Movie> readGender(@PathVariable(value = "gender") String gender){
		List<Movie> movies = StreamSupport
				.stream(movieService.readGender(gender).spliterator(), false)
				.collect(Collectors.toList());
		return movies;
	}
	
	@GetMapping("/order/{order}")
	public List<Movie> readAllOrder(@PathVariable(value = "order") String orderDate){
		
		if(orderDate.equals("ASC")) {
			List<Movie> movies = StreamSupport
					.stream(movieService.findAllOrderAsc().spliterator(), false)
					.collect(Collectors.toList());
			return movies;
		}else{
			List<Movie> movies = StreamSupport
					.stream(movieService.findAllOrderDesc().spliterator(), false)
					.collect(Collectors.toList());
			return movies;
		}
		
	}
}
