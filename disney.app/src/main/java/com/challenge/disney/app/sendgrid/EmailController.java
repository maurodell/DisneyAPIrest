package com.challenge.disney.app.sendgrid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.disney.app.sendgrid.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/sendmail/{email}")
	public String sendEmail(@PathVariable(value = "email", required = true) String email) {
		
		return emailService.sendEmail(email);
	}
}
