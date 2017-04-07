package com.dpd.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	EmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	private void init() {
		Employee emp = new Employee();
		emp.setFirstName("Thiru");
		emp.setLastName("Chinna");
		emp.setTitle("Architect");
		repository.save(emp);

	}
}
