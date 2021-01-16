package com.luka.trackerapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebAppSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService detailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(detailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		
		return provider;
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.authorizeRequests()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.and()
//		.httpBasic();
//	}

	
	
}
