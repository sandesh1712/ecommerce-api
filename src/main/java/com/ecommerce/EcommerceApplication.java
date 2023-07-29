package com.ecommerce;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "config","controller", "service", "error", "exception", "helper", "types", "advice",
		"DTO" ,})
@EntityScan(basePackages = "entity")
@EnableJpaRepositories(basePackages = { "repository" })
public class EcommerceApplication {

	public static void main(String[] args) {
		Properties props = new Properties();
		
		String databaseString = System.getenv("DB_HOST");
		if(databaseString!=null)
     		props.put("spring.datasource.url","spring.datasource.url=jdbc:mysql://"+databaseString+":3306/ecommerce"); 
		
		if(props.size()==0)
		 SpringApplication.run(EcommerceApplication.class,args);
		else 
		 new SpringApplicationBuilder(EcommerceApplication.class).properties(props);	
	}

}
