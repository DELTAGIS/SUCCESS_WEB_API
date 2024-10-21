package com.deltagis.success;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ca.deltagis.success API", version = "1.0", description = ""))
public class SuccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuccessApplication.class, args);
	}

}
