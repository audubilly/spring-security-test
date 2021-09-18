package com.billy.testdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDemoApplication.class, args);
	}

	@GetMapping({"/user"})
	public String getUser(){
		return "Hello User";
	}

	@GetMapping({"/admin"})
	public String getAdmin(){
		return "Hello Admin";
	}


}
