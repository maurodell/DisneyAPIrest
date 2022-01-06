package com.challenge.disney.app.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.disney.app.security.entity.User;
import com.challenge.disney.app.security.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public Optional<User> getByUsername(String userName){
		return userRepository.findByUsername(userName);
	}
	public boolean existsByUsername(String user) {
		return userRepository.existsByUsername(user);
	}
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	public void save(User user) {
		userRepository.save(user);
	}
}
