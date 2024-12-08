package com.ecom.demo.eecom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.demo.eecom.pojo.LoginApiDetails;

@Service
public class AuthService {
	
	//login
	//signup
	//resert password
	
	
	@PostMapping("v2/login")
	  public String login(@RequestBody LoginApiDetails loginApiDetails) {
	    String dbEmail = "Anji@gmail.com";
	    String dbPassword = "Anji@123";

	    if (dbEmail.equals(loginApiDetails.getEmail())
	        && dbPassword.equals(loginApiDetails.getPassword())) {
	      return "Login Success";
	    } else {
	      return "check email or password";
	    }
	  }
	
	@PostMapping("v2/signup")
	public String signup(@RequestParam) {
		return;
	}
	
	

}
