package com.ecom.demo.eecom.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GobalExceptions  {

@ExceptionHandler(Exception.class)
public ResponseEntity<Object> allExceptionsHandlerEntity(Exception ex){
//hashmap>assign message to values>set Http satus code

Map<String,String> resMap = new HashMap<>();
resMap.put("Message", ex.getMessage());
resMap.put("Status", "Failed");

return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resMap);
}


//methodInvalidExceptions
//>Exception name and object>map>BindResult>put message

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Object> handleApiValidationResponseEntity(MethodArgumentNotValidException ex){
    Map<String, String> errorMap = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
        errorMap.put(error.getField(), error.getDefaultMessage());

    });
Map<String, Object>responseMap = new HashMap<>();
responseMap.put("Message","Ubable to process your requst");
responseMap.put("Status", "Failed");
responseMap.put("errors", errorMap);

return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);



}







}
