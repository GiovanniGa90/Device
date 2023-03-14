package com.giovannigalatioto.FrydayW5.EsFridayW5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Dispositivo;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.StatoDisp;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.TypeDevice;

@Repository
public interface DispositiviRepo extends JpaRepository<Dispositivo, Integer> {
	
	@Modifying
	@Query("update dispositivi SET user_id = :userid, stato = 'ASSEGNATO' where id = 1" )
	void assegnaDispositivo(@Param("userid") int userid, @Param ("stato") StatoDisp s, @Param("id") int id);
	
	@Query("SELECT * FROM dispositivi WHERE stato = :stato")
	List<Dispositivo> getDispByStato(@Param("stato")String s);


}
