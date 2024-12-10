package com.ecom.demo.eecom.Controller;

import com.ecom.demo.eecom.pojo.LoginApiDetails;
import com.ecom.demo.eecom.pojo.ResetApiDetails;
import com.ecom.demo.eecom.pojo.signupApiDetails;
import com.ecom.demo.eecom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AuthController {

  @Autowired public AuthService authService;

  @PostMapping("v2/login")
  public String login(@RequestBody LoginApiDetails loginApiDetails) {
    String responseString = authService.login(loginApiDetails);
    return responseString;
  }

  @PostMapping("v2/signup")
  public String signup(@RequestBody signupApiDetails SignupApiDaTA) {
    String signupResponseString = authService.signup(SignupApiDaTA);
    return signupResponseString;
  }

  @PostMapping("v2/resetPassword")
  public String resetPassword(@RequestBody ResetApiDetails resetApiDetails) {
    String resetResponseString = authService.resetPassword(resetApiDetails);
    return resetResponseString;
  }
}
