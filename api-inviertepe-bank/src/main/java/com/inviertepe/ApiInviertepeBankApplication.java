package com.inviertepe;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Banco InviertePE API",
        description = "Documentation about apis from the Bank InviertePE",
        version = "1.0.2"), servers = {
        @Server(url = "http://localhost:8082/",
                description = "local server")
})
public class ApiInviertepeBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiInviertepeBankApplication.class, args);
	}

}
