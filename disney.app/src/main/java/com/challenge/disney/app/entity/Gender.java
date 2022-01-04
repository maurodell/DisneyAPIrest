package com.challenge.disney.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genders")
public class Gender implements Serializable{
	private static final long serialVersionUID = 4181434632188409785L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gender", nullable = false, unique = true)
	private Long idGender;
	
	@Column(length = 50)
	private String name;
	
	@Column(name = "img")
	private String img;
	
	@OneToMany(mappedBy = "gender")
	private List<Movie> movies;
	
	
	public Gender(String name, String img, List<Movie> movies) {
		super();
		this.name = name;
		this.img = img;
		this.movies = movies;
	}

	public Gender() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdGender() {
		return idGender;
	}

	public void setIdGender(Long idGender) {
		this.idGender = idGender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	
}
