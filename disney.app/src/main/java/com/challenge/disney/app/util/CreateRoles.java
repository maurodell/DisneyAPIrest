package com.challenge.disney.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.challenge.disney.app.security.entity.Rol;
import com.challenge.disney.app.security.enums.RolName;
import com.challenge.disney.app.security.service.RolService;
/*
@Component
public class CreateRoles implements CommandLineRunner{
	@Autowired
	RolService rolService;
	
	@Override
	public void run(String... args) throws Exception {
		Rol rolAdmin = new Rol(RolName.ROLE_ADMIN);
		
		Rol rolUser = new Rol(RolName.ROLE_USER);
		
		rolService.save(rolAdmin);
		
		rolService.save(rolUser);
	}
}*/
