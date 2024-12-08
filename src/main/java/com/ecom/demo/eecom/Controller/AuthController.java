package com.ecom.demo.eecom.Controller;

import com.ecom.demo.eecom.pojo.LoginApiDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
  //    @PostMapping("v2/login")
  //    public String login(@RequestBody LoginApiDetails loginApiDetails){
  //        return "Under Development "+loginApiDetails.toString();
  //    }

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
}
