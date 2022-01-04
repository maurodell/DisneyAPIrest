package com.challenge.disney.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.disney.app.entity.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>{
	
	@Query("SELECT c FROM Character c WHERE c.name LIKE :name")
	public Optional<Character> searchByName(@Param("name") String name);
	
	@Query("SELECT c FROM Character c WHERE c.age LIKE :age")
	public List<Character> searchByAge(@Param("age") Integer age);
	
	@Query("SELECT c.img, c.name FROM Character c")
	public Iterable<Character> readAllDefined();
}
