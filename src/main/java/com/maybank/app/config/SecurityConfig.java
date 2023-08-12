package com.maybank.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		// Allow access to H2 Console
		web.ignoring().antMatchers("/h2-console/**"); // Adjust the URL pattern as needed
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// Disable CSRF protection
		http.csrf(csrf -> csrf.disable());
		
		// Configure authorization rules
		http.csrf().disable().authorizeRequests().antMatchers("/h2-console/**").permitAll().anyRequest().authenticated()
				.and().httpBasic(); // Use HTTP Basic Authentication

		// Disable X-Frame-Options to allow H2 Console to be displayed in frames
		http.headers().frameOptions().disable();
	}
}
