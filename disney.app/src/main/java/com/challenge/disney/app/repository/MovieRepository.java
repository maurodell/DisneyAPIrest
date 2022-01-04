package com.challenge.disney.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.disney.app.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
