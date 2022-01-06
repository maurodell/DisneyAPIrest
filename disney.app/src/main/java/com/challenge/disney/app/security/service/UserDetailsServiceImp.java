package com.challenge.disney.app.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; 

import com.challenge.disney.app.security.entity.PrimaryUser;
import com.challenge.disney.app.security.entity.User;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUsername(username).get();
		
		return PrimaryUser.build(user);
	}
	
}
