package com.challenge.disney.app.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
	private String token;
	private String bearer = "Bearer";
	private String userName;
	private Collection<? extends GrantedAuthority> authotiries;
	
	public JwtDto(String token, String userName, Collection<? extends GrantedAuthority> authotiries) {
		super();
		this.token = token;
		this.userName = userName;
		this.authotiries = authotiries;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Collection<? extends GrantedAuthority> getAuthotiries() {
		return authotiries;
	}

	public void setAuthotiries(Collection<? extends GrantedAuthority> authotiries) {
		this.authotiries = authotiries;
	}
	
}
