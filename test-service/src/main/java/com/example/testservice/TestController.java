package com.example.testservice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping( value = "/")
    public ResponseEntity<?> testDeneme(){
      List<String> list =  System.getenv().values().stream()
                .map(itr->itr)
                .collect(Collectors.toList());
     return new ResponseEntity<List>(list,HttpStatus.ACCEPTED) ;
    }


}
