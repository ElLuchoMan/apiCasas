package com.prueba;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


public class WebSecurity {
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests().antMatchers("/api/auth/**").permitAll().antMatchers("/api/test/**")
				.permitAll() // permit the class of test
				.antMatchers("/**").permitAll() // permit all the routers after swagger-ui.html
				.anyRequest().authenticated();
	}
}
