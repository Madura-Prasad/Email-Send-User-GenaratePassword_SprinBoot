package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User;
import com.example.demo.Model.passwordGenerate;
import com.example.demo.Repository.userRepo;


@RestController
public class userController {

	
	@Autowired
	private userRepo repo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@PostMapping("/save")
	public String saveUser(@RequestBody User user) {
		try {
			//Generate Password
			String strongPassword = passwordGenerate.generateStrongPassword(12); // Change the length as needed
	        user.setPassword(strongPassword);
	        
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setSubject("Hello "+ user.getName() +"!  Registration Successfully..");
			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom("maduraprasa00@gmail.com");
			mailMessage.setText("<html><body style='text-align:center;'>" +
			        "<h1>Thank you for registering with us!</h1>" +
			        "<h3>Your registration details:</h3>" +
			        "<p><strong>Name:</strong> " + user.getName() + "</p>" +
			        "<p><strong>Email:</strong> " + user.getEmail() + "</p>" +
			        "<p><strong>Password:</strong> " + user.getPassword() + "</p>" +
			        "</body></html>");

			
			javaMailSender.send(mailMessage);
			System.out.println(mailMessage.toString());
			
			repo.save(user);
			
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Success";
		
		
	}
}
