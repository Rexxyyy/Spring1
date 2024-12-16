package com.ecom.demo.eecom.Controller;

import com.ecom.demo.eecom.pojo.LoginApiDetails;
import com.ecom.demo.eecom.pojo.ResetApiDetails;
import com.ecom.demo.eecom.pojo.signupApiDetails;
import com.ecom.demo.eecom.service.AuthService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/v2/login")
  public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginApiDetails loginApiDetails,
      BindingResult validationResult) {

    if (validationResult.hasErrors()) { // if validation errors are present

      Map<String, String> errors = new HashMap<>(); // HashMap to store errors

      validationResult.getFieldErrors().forEach(error -> // getFieldErrors() + Lambda for each error
      errors.put(error.getField(), error.getDefaultMessage()));// obj.put(key, value) });

      Map<String, Object> errorResponse = new HashMap<>(); // HashMap to store error response
      errorResponse.put("Result", "Failed");
      errorResponse.put("Error", errors);
      errorResponse.put("Message", "Validation Error");
      errorResponse.put("No of attempt", 6);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    } else {
      String loginResponseString = authService.login(loginApiDetails); //

      // Map to store success response
      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("login-result", loginResponseString); // putting loginresult in response
      successResponse.put("result", "Success");

      // returning success response
      Map<String, Object> userDetails = new HashMap<>();
      userDetails.put("name", "anji");
      userDetails.put("email", loginApiDetails.getEmail());
      userDetails.put("phone", "1234567890");
      userDetails.put("address", "hyd");
      userDetails.put("pincode", "500001");

      successResponse.put("userDetails", userDetails);
      return ResponseEntity.status(HttpStatus.OK).body(successResponse);
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
