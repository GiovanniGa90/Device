package com.giovannigalatioto.FrydayW5.EsFridayW5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Utente;

@Repository
public interface UtenteRepo extends JpaRepository<Utente,Integer> {

}
