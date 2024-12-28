package com.ecom.demo.eecom.pojo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpApiData {

@NotNull(message = "Name cannot be null")
@NotBlank(message = "Name cannot be blank")
@Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name must contain only alphabets")
    private String name;



@NotNull(message = "Email cannot be null")
@NotBlank(message = "Email cannot be blank")
@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Invalid email")
    private String email;


@NotNull(message = "Password cannot be null")
@NotBlank(message = "Password cannot be blank")
@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", message = "Password must contain at least one digit, one lowercase, one uppercase, one special character")
    private String password;


@NotNull(message = "Mobile cannot be null")
@NotBlank(message = "Mobile cannot be blank")
@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobile;
}


