package com.giovannigalatioto.FrydayW5.EsFridayW5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Dispositivo;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Role;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.RoleType;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.TypeDevice;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.StatoDisp;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Utente;

@Configuration
public class Beans {
	
	@Bean
	@Scope("protopype")
	public Utente utente( String name, String lastname, String username, String email, String password) {
		
		return Utente.builder()
				.name(name)
				.lastname(lastname)
				.email(email)				
				.username(username)
				.password(password)
				.build();
	}
	
	@Bean 
	@Scope("prototype")
	public Dispositivo dispositivi(TypeDevice disposito, StatoDisp statoDisp) {
		
		return Dispositivo.builder()
				.dispositivo(disposito)
				.statoDisp(statoDisp)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Role role(RoleType r) {
		return Role.builder()
				.role(r)
				.build();
	}
}
