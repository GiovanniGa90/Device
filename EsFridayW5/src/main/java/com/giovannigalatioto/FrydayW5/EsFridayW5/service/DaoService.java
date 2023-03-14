package com.giovannigalatioto.FrydayW5.EsFridayW5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Dispositivo;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Role;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.StatoDisp;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.TypeDevice;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Utente;
import com.giovannigalatioto.FrydayW5.EsFridayW5.repository.DispositiviRepo;
import com.giovannigalatioto.FrydayW5.EsFridayW5.repository.RoleRepo;
import com.giovannigalatioto.FrydayW5.EsFridayW5.repository.UtenteRepo;


@Service
public class DaoService {
	
	@Autowired
	private UtenteRepo utenteRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private DispositiviRepo dispRepo;
	
	public Optional<Utente> getUtenteById(int id){
		return utenteRepo.findById(id);
	}
	
	public Utente saveUtente(Utente obj) {
		return utenteRepo.save(obj);	
		}

	public Role saveRole(Role obj) {
		return  roleRepo.save(obj);
		}
	
	public Dispositivo saveDispositivo(Dispositivo obj) {
		return dispRepo.save(obj);
	}
	public void update(int userid, StatoDisp s, int id) {
		dispRepo.assegnaDispositivo(userid, s, id);
	}
	
	public List<Dispositivo> getAllDispos() {
		return dispRepo.findAll();
	}
	
	public List<Dispositivo> getDispByStato(String s) {
		return dispRepo.getDispByStato(s);
	}
}
