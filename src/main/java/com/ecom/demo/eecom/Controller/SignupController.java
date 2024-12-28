package com.ecom.demo.eecom.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.demo.eecom.pojo.SignUpApiData;



public class SignupController {



    @PostMapping("/create")

    public Object signup(@RequestBody SignUpApiData signUpApiData) {
        return signUpApiData;
    }

}
