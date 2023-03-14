package com.giovannigalatioto.FrydayW5.EsFridayW5.config;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Role;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Utente;
import com.giovannigalatioto.FrydayW5.EsFridayW5.service.DaoService;



@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DaoService ds;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	http
		.authorizeRequests()					
			.antMatchers("/")
			.permitAll()
		.anyRequest()
			.authenticated()
		.and()
		.formLogin()
		.and()
		.logout();
	}
	
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		
		Optional<Utente> authUtenteObj = ds.getUtenteById(1);
		Utente authUtente = authUtenteObj.get();
		String role = "UTENTE";
		Set<Role> roles = authUtente.getRoles();
		
		for( Role r : roles ) {
			if( r.getRole().toString().contains("ADMIN") ) {
				role = "ADMIN";
				break;
			}
		}
		auth.inMemoryAuthentication()
		.withUser( authUtente.getName() ).password( passwordEncoder().encode( authUtente.getPassword() ) )
		.roles(role);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
