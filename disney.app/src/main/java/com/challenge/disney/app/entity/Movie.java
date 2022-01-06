package com.challenge.disney.app.entity;

import java.io.Serializable;
import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "movies")
public class Movie implements Serializable{
	
	private static final long serialVersionUID = 4136063742141533041L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movie", nullable = false, unique = true)
	private Long idMovie;
	
	@Column(length = 50)
	private String title;
	
	@Column(name = "img", nullable = true)
	private String img;
	
	@Column(name = "creation_date")
	//@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MMM-yyyy", locale="es")
	private Date date;
	
	@Column(name = "qualification", nullable = true)
	private Integer qualification;
	
	@ManyToOne
	@JoinColumn(name = "mo_character", nullable = true)
	private Character character;
	
	@ManyToOne
	@JoinColumn(name = "mo_gender")
	private Gender gender;
	
	
	
	public Movie(String title, String img, Date date, Integer qualification, Character character, Gender gender) {
		super();
		this.title = title;
		this.img = img;
		this.date = date;
		this.qualification = qualification;
		this.character = character;
		this.gender = gender;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Long idMovie) {
		this.idMovie = idMovie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getQualification() {
		return qualification;
	}

	public void setQualification(Integer qualification){
		this.qualification = qualification;
		
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
