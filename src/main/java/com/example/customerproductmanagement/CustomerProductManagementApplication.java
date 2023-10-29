package com.example.customerproductmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "DAO")
@EntityScan(basePackages = "com/example/customerproductmanagement/Entity")
public class CustomerProductManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerProductManagementApplication.class, args);
	}

}
