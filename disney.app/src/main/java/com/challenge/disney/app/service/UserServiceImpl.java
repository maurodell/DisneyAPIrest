package com.challenge.disney.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.disney.app.entity.User;
import com.challenge.disney.app.repository.UserRespository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRespository userRespository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRespository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return userRespository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		
		return userRespository.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRespository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRespository.deleteById(id);
	}

}
