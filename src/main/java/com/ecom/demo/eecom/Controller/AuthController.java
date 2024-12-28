package com.ecom.demo.eecom.Controller;

import com.ecom.demo.eecom.entity.User;
import com.ecom.demo.eecom.exceptions.GobalExceptions;
import com.ecom.demo.eecom.exceptions.InvalidPasswordException;
import com.ecom.demo.eecom.exceptions.UserNotFoundException;
import com.ecom.demo.eecom.pojo.LoginApiDetails;
import com.ecom.demo.eecom.pojo.ResetApiDetails;
import com.ecom.demo.eecom.pojo.SignUpApiData;
import com.ecom.demo.eecom.repository.UserRepository;
import com.ecom.demo.eecom.pojo.ProfileUpdateApiData;
import com.ecom.demo.eecom.service.AuthService;
import com.ecom.demo.eecom.pojo.GetUserByEmailApiData;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Autowired GobalExceptions exceptions;

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
  public Object signup(@RequestBody SignUpApiData signUpApiData) {
    User newUser = authService.signup(signUpApiData);
    return newUser;
  }

  // Pojo(body)>conrtroller(call+object creation)>service>repository>db

  @PostMapping("/v2/resetPassword")
  public String resetPassword(@RequestBody ResetApiDetails resetApiDetails) {
    String resetResponseString = authService.resetPassword(resetApiDetails);
    return resetResponseString;
  }
//UPDATE PASSWORD
  @PostMapping("/password-update")
  public ResponseEntity<Map<String, String>> passwordUpdate(@RequestBody ProfileUpdateApiData profileUpdateApiData) {

    Map<String, String> responseMap = new HashMap<>();
    try {
      authService.passwordUpdate(profileUpdateApiData);
      responseMap.put("Result", "true");
      responseMap.put("Message", "Pasword updated successfully");
      return ResponseEntity.ok(responseMap);
    } catch (UserNotFoundException e) {
      responseMap.put("Result", "false");
      responseMap.put("Message", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);

    } catch (InvalidPasswordException e) {
      responseMap.put("Result", "false");
      responseMap.put("Message", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
    } catch (Exception e) {
      responseMap.put("Result", "false");
      responseMap.put("Message", "Something went wrong");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
    }
  }
// UPDATE NAME
  @PostMapping("/name-update")
  public Map<String, String> nameUpdate(@RequestBody ProfileUpdateApiData profileUpdateApiData) {

    Boolean updateResponse = authService.nameUpdate(profileUpdateApiData);
    Map<String, String> responseMap = new HashMap<>();
    if (updateResponse == true) {
      responseMap.put("Result", "true");
      responseMap.put("Message", "Name updated successfully");
    } else {
      responseMap.put("Result", "false");
      responseMap.put("Message", "User not found");
    }
    return responseMap;

  }

  // GET USER DETAILS

  @GetMapping("user/{id}")
  public ResponseEntity<Map<String, Object>> getUserDetails(@PathVariable int id) {
    Optional<User> dataOptional = authService.getUserDetails(id);

    Map<String, Object> responseMap = new HashMap<>();

    if (dataOptional.isPresent()) {
      responseMap.put("Result", "Success");
      responseMap.put("Message", "User found");
      responseMap.put("Data", dataOptional.get());
      return ResponseEntity.ok(responseMap);
    } else {
      responseMap.put("Result", "Failed");
      responseMap.put("Message", "User not found");
      responseMap.put("Data", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);

    }

  }
@Autowired UserRepository userRepository;

  @PostMapping("/get-user-by-email")
  public Object getUserByEmail(@RequestBody GetUserByEmailApiData getUserByEmailApiData) {
    return  authService.getUserByEmail(getUserByEmailApiData);
  }
  




  @PostMapping("loginWithQuery")
  public Object loginWithSqlQuery(@RequestBody @Valid LoginApiDetails loginApiDetails) {
    return authService.loginWithSqlQuery(loginApiDetails);
  }





  @PostMapping("login2")
  public  Object login2(@RequestBody @Valid LoginApiDetails loginApiDetails){
  return authService.login2(loginApiDetails);
  }


}
