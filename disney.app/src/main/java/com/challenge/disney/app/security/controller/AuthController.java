package com.challenge.disney.app.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.disney.app.security.Message;
import com.challenge.disney.app.security.dto.JwtDto;
import com.challenge.disney.app.security.dto.LoginUser;
import com.challenge.disney.app.security.dto.Newusers;
import com.challenge.disney.app.security.entity.Rol;
import com.challenge.disney.app.security.entity.User;
import com.challenge.disney.app.security.enums.RolName;
import com.challenge.disney.app.security.jwt.JwtProvider;
import com.challenge.disney.app.security.service.RolService;
import com.challenge.disney.app.security.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/register")
	public ResponseEntity<?> Newuser(@Valid @RequestBody Newusers newUser, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("campos mal puesto o mail invalido"), HttpStatus.BAD_REQUEST);
		}
		if(userService.existsByUsername(newUser.getName())) {
			return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		}
			
		if(userService.existsByEmail(newUser.getEmail())) {
			return new ResponseEntity(new Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
		}
			
		
		User user = 
				new User(newUser.getName(), newUser.getUsername(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword())); 
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
		if(newUser.getRoles().contains("admin"))
			roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
		user.setRoles(roles);
		userService.save(user);
		return new ResponseEntity(new Message("usuario guardado"), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity(new Message("campos mal puesto"), HttpStatus.BAD_REQUEST);
		Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
}
