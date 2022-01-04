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
@Table(name = "characters")
public class Character implements Serializable{

	private static final long serialVersionUID = -1300579529836117581L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_character", nullable = false, unique = true)
	private Long idCharacter;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "weight")
	private double weight;
	
	@Column(name = "history")
	private String history;
	
	@OneToMany(mappedBy = "character")
	private List<Movie> movies;
	

	public Character(String name, String img, Integer age, double weight, String history, List<Movie> movies) {
		super();
		this.name = name;
		this.img = img;
		this.age = age;
		this.weight = weight;
		this.history = history;
		this.movies = movies;
	}

	public Character() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdCharacter() {
		return idCharacter;
	}
	
	public void setIdCharacter(Long idCharacter) {
		this.idCharacter = idCharacter;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	
}
