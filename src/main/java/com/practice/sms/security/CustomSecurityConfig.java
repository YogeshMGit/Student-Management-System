package com.practice.sms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user123").roles("USER").build();

		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin123").roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/students/new").hasRole("ADMIN")
		.requestMatchers("/students/edit/{id}").hasRole("ADMIN")
		.requestMatchers("/students/delete/{id}").hasRole("ADMIN")
		.requestMatchers("/").authenticated()
		.requestMatchers("/**").authenticated().and().formLogin().defaultSuccessUrl("/liststudents").and().logout().logoutUrl("/logout");
		//.deleteCookies("JSESSIONID").invalidateHttpSession(true) ;
		return http.build();
	}
}
