package com.ecom.demo.eecom.service;

import com.ecom.demo.eecom.pojo.ResetApiDetails;
import org.springframework.stereotype.Service;
import com.ecom.demo.eecom.pojo.LoginApiDetails;
import com.ecom.demo.eecom.pojo.signupApiDetails;

@Service
public class AuthService {

  // login
  // signup
  // reset password

  public String login(LoginApiDetails loginApiDetails) {


    // if (dbEmail.equals(loginApiDetails.getEmail().toLowerCase())
    //     && dbPassword.equals(loginApiDetails.getPassword())) {
    //   return "Login Success";
    // } else {
    //   return "check email or password";
    // } 
    return "Login Success";
  }

  public String signup( signupApiDetails signupApiDetails) {
    return "Verification email sent, please check";
  }

  public String resetPassword(ResetApiDetails resetApiDetails){
    return "If account with this email exits, resetPassword instruction will be sent, please check email";

  }

}

