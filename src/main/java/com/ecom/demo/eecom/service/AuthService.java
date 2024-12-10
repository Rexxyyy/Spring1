package com.ecom.demo.eecom.service;

import com.ecom.demo.eecom.pojo.ResetApiDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.demo.eecom.pojo.CommentApiDetails;
import com.ecom.demo.eecom.pojo.LoginApiDetails;
import com.ecom.demo.eecom.pojo.signupApiDetails;

@Service
public class AuthService {

  // login
  // signup
  // resert password

  public String login(LoginApiDetails loginApiDetails) {
    String dbEmail = "Anji@gmail.com";
    String dbPassword = "Anji@123";

    if (dbEmail.equals(loginApiDetails.getEmail())
        && dbPassword.equals(loginApiDetails.getPassword())) {
      return "Login Success";
    } else {
      return "check email or password";
    }
  }

  public String signup( signupApiDetails signupApiDetails) {
    return "Verification email sent, please check";
  }

  public String resetPassword(ResetApiDetails resetApiDetails){
    return "If account with this email exits, resetPassword instruction will be sent, please check email";

  }

}

