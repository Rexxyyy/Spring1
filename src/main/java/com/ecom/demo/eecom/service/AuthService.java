package com.ecom.demo.eecom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.ecom.demo.eecom.pojo.LoginApiDetails;

import com.ecom.demo.eecom.pojo.signupApiDetails;

@Service
public class AuthService {

  // login
  // signup
  // resert password

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

public String signup (@RequestBody signupApiDetails SignupApiDetails) {


	
	return "Verification email sent, please check";
	
}
}
