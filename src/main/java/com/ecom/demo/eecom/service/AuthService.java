package com.ecom.demo.eecom.service;

import com.ecom.demo.eecom.pojo.ResetApiDetails;
import com.ecom.demo.eecom.pojo.SignUpApiData;
import com.ecom.demo.eecom.repository.UserRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.demo.eecom.entity.User;
import com.ecom.demo.eecom.exceptions.InvalidPasswordException;
import com.ecom.demo.eecom.exceptions.UserNotFoundException;
import com.ecom.demo.eecom.pojo.LoginApiDetails;
import com.ecom.demo.eecom.pojo.ProfileUpdateApiData;

@Service
public class AuthService {

  @Autowired
  public UserRepository userRepository;

  public String login(LoginApiDetails loginApiDetails) {

    String dbEmail = "Anji@gmail.com";
    String dbPassword = "Anji@123";

    if (dbEmail.equals(loginApiDetails.getEmail().toLowerCase())
        && dbPassword.equals(loginApiDetails.getPassword())) {
      return "Login Success";
    } else {
      return "CHECK EMAIL OR PASSWORD";
    }
  }

  // Signup Service- to save data in db
  // void replaced with User object- to return user id
  public User signup(SignUpApiData signUpApiData) {

    User user = new User();
    // Assigning values to 'user' object
    user.setName(signUpApiData.getName());
    user.setMobile(signUpApiData.getMobile());
    user.setEmail(signUpApiData.getEmail());
    user.setPassword(signUpApiData.getPassword());

    // calling repository to save 'user'data in db
    User newUser = userRepository.save(user);
    return newUser;
  }

  public User passwordUpdate(ProfileUpdateApiData profileUpdateApiData) {

    // 1. Check data with DB - throw exception if not found
    User user = userRepository.findById(profileUpdateApiData.getId())
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    // 2. Validate password policy
    String oldPassword = user.getPassword();
    String newPassword = profileUpdateApiData.getPassword();

    if (newPassword.equals(oldPassword)) {
      throw new InvalidPasswordException("New password cannot be same as old password");
    }
    if (newPassword == null || newPassword.isEmpty() || newPassword.length() < 8) {
      throw new InvalidPasswordException("Invalid Password");
    }
    // 3. Update password and return success
    user.setPassword(newPassword);
    return userRepository.save(user);
  }

  // GET USER DETAILS
  public Optional<User> getUserDetails(int id) {
    Optional<User> dbResponse = userRepository.findById(id);
    return dbResponse;
  }

  public boolean nameUpdate(ProfileUpdateApiData profileUpdateApiData) {
    Optional<User> dbResponse = userRepository.findById(profileUpdateApiData.getId());

    if (dbResponse.isPresent() == true) {
      User user = dbResponse.get();
      user.setName(profileUpdateApiData.getName());
      userRepository.save(user);
      return true;
    } else {
      return false;
    }
  }

  public String resetPassword(ResetApiDetails resetApiDetails) {
    return "If account with this email exits, resetPassword instruction will be sent, please check email";
  }

}
