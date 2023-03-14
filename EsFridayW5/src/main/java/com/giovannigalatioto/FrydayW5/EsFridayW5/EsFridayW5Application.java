package com.giovannigalatioto.FrydayW5.EsFridayW5;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.giovannigalatioto.FrydayW5.EsFridayW5.config.Beans;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Dispositivo;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Role;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.RoleType;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.TypeDevice;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.StatoDisp;
import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Utente;
import com.giovannigalatioto.FrydayW5.EsFridayW5.service.DaoService;




@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EsFridayW5Application implements CommandLineRunner{

	
	@Autowired
	private DaoService ds;
	
	public static void main(String[] args)  {
		SpringApplication.run(EsFridayW5Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		
		Utente u1 = (Utente)ctx.getBean("utente", "Giovanni", "Galatioto", "GioGio","giovanni@gmail.com","gufo");
		
		Role r1 = (Role)ctx.getBean("role", RoleType.USER);
		Role r2 = (Role)ctx.getBean("role", RoleType.ADMIN);
		
		u1.setRoles(new HashSet<>(){{
			add(r1);
		}});
		
		Dispositivo d1 = (Dispositivo)ctx.getBean("dispos", TypeDevice.LAPTOP, StatoDisp.DISPONIBILE);
		Dispositivo d2 = (Dispositivo)ctx.getBean("dispos", TypeDevice.SMARTPHONE, StatoDisp.DISMESSO);
		Dispositivo d3 = (Dispositivo)ctx.getBean("dispos", TypeDevice.TABLET, StatoDisp.MANUTENZIONE);
		Dispositivo d4 = (Dispositivo)ctx.getBean("dispos", TypeDevice.LAPTOP, StatoDisp.DISPONIBILE);
		Dispositivo d5 = (Dispositivo)ctx.getBean("dispos", TypeDevice.TABLET, StatoDisp.DISPONIBILE);
		
		((AnnotationConfigApplicationContext)ctx).close();
		

		//populateRole(r1);
		//populateRole(r2);
		
		//populateUsers(u1);
		
//		populateDispositivi(d1);
//		populateDispositivi(d2);
//		populateDispositivi(d3);
//		populateDispositivi(d4);
//		populateDispositivi(d5);
		
		//assegnaDispos(d4, u1);
	}
	
	public void populateRole(Role r) {
		ds.saveRole(r);
	}
	
	public void populateUsers(Utente u) {
		ds.saveUtente(u);
	}
	
	public void populateDispositivi(Dispositivo d) {	
		ds.saveDispositivo(d);
	}
	
	private void getRolesFromUserById(int id) {
        Optional<Utente> authUserObj = ds.getUtenteById(id);
        Utente authUser = authUserObj.get();
        String role = "USER";
        Set<Role> roles = authUser.getRoles();

        for( Role r : roles ) {
            if( r.getRole().toString().contains("ADMIN") ) {
                role = "ADMIN";
                break;
            }
        }

        System.out.println(role);
    }
	
	public void assegnaDispos(Dispositivo d, Utente u) {
			
		String s = null;
		
		switch(d.getStatoDisp()) {
		
		case DISPONIBILE:
			ds.update(u.getId(), StatoDisp.ASSEGNATO, d.getId());
			s = "Dispositivo assegnato correttamente all'utente" + u.getName() + u.getLastname();
			break;
		
		case ASSEGNATO:
			s = "Dispositivo occupato";
			break;
			
		case DISMESSO:
			s = "Dispositivo non disponibile";
			break;
			
		case MANUTENZIONE:
			s = "Dispositivo in manutenzione";
			break;
		}
		System.out.println(s);	
	}
}
