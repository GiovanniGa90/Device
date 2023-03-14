package com.giovannigalatioto.FrydayW5.EsFridayW5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Dispositivo;
import com.giovannigalatioto.FrydayW5.EsFridayW5.service.DaoService;

@Controller
public class AppController {
	
	@Autowired
	DaoService ds;
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Home page";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	private String admin() {
		return "Pagina dedicata al ADMIN";
	}
	@GetMapping("/dispositivi")
	@ResponseBody
	private List<Dispositivo> allDispos() {
		return ds.getAllDispos();
	}
	@GetMapping("/dispositivi/{stato}")
	@ResponseBody
	
	public List<Dispositivo> stato(@PathVariable String stato) {
		return ds.getDispByStato(stato.toUpperCase());
	}
}
