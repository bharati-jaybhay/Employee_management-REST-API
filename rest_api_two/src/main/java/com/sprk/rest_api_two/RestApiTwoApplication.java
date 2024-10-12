package com.sprk.rest_api_two;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Rest API of Employee",
				description = "Industry Oriented REST API for SPRK Students.",

				contact = @Contact(
						name = "Bharati jaybhay",
						email = "bharatijaybhay12@gmail.com",
						url = "https://www.linkedin.com/in/bharati-jaybhay/"
				),

				license = @License(
						name = "Apache 2.0",
						url = "https://www.sprktechnologies.in/"
				),
				version = "v1"
		),
		externalDocs = @ExternalDocumentation(
				description = "REST API External Docs",
				url = "http://localhost:8080/swagger-ui.html"
		),
		extensions = {
				@Extension(name = "x-company-metadata", properties = {
						@ExtensionProperty(name = "department", value = "Engineering"),
						@ExtensionProperty(name = "project", value = "Employee API Development")
				}),
				@Extension(name = "x-feature-flags", properties = {
						@ExtensionProperty(name = "featureXEnabled", value = "true"),
						@ExtensionProperty(name = "featureYEnabled", value = "false")
				})
		}
)

public class RestApiTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiTwoApplication.class, args);
	}

}