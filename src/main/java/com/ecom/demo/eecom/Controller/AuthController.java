package com.ecom.demo.eecom.Controller;

import com.ecom.demo.eecom.pojo.LoginApiDetails;
import com.ecom.demo.eecom.pojo.ResetApiDetails;
import com.ecom.demo.eecom.pojo.signupApiDetails;
import com.ecom.demo.eecom.service.AuthService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AuthController {

private AuthService authService;
public AuthController(AuthService authService) {
  this.authService = authService;
  }

  @PostMapping("/v2/login")
  public String login(@Valid @RequestBody LoginApiDetails loginApiDetails, BindingResult validationResult ) {

    System.out.println(validationResult.hasErrors());

      if (validationResult.hasErrors()== true) {    //if validation errors are present

          Map<String, String> errors = new HashMap<>();       //HashMap to store errors

            validationResult.getFieldErrors().forEach(error->{  //getFieldErrors() + Lambda for each error
            errors.put(error.getField(), error.getDefaultMessage()); //obj.put(key, value)
            });

            errors.put("result","Failed"); //putting result as failed
    System.out.println(errors);  //printing errors in console for debugging
    return errors.toString();  //returning errors
    
  } else{
    String loginResponseString = authService.login(loginApiDetails); //
    
    Map<String, String> response = new HashMap<>(); 
    response.put("loginresult", loginResponseString); //putting loginresult in response
    response.put ("result", "Success");
    return loginResponseString;
  }
}

  @PostMapping("/v2/signup")
  public String signup(@RequestBody signupApiDetails SignupApiDaTA) {
    String signupResponseString = authService.signup(SignupApiDaTA);
    return signupResponseString;
  }

  @PostMapping("/v2/resetPassword")
  public String resetPassword(@RequestBody ResetApiDetails resetApiDetails) {
    String resetResponseString = authService.resetPassword(resetApiDetails);
    return resetResponseString;
  }
}
