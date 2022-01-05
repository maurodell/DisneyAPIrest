package com.challenge.disney.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.disney.app.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query("SELECT c.img, c.title, c.date FROM Movie c")
	public Iterable<Movie> readAllDefined();
	
	@Query("SELECT c FROM Movie c WHERE c.title LIKE :title")
	public Optional<Movie> searchByTitle(@Param("title") String title);
	
	@Query("SELECT c FROM Movie c WHERE c.gender LIKE :gender")
	public Iterable<Movie> readGender(@Param("gender") String gender);

	@Query("SELECT c FROM Movie c ORDER BY DATE(c.date) ASC")
	public Iterable<Movie> readAllOrderAsc();
	
	@Query("SELECT c FROM Movie c ORDER BY DATE(c.date) DESC")
	public Iterable<Movie> readAllOrderDesc();
}
