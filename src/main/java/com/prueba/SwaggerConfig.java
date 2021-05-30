
package com.prueba;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.prueba.controller")).paths(PathSelectors.any()).build()
				.apiInfo(getApiInfo());
	}

	@SuppressWarnings("unused")
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Casas - Personas").description("Primera implemetacion de Swagger")
				.contact(new Contact("Luchoman", "https://github.com/ElLuchoMan", "baluisto96@gmail.com"))
				.version("1.0").build();
	}
}
