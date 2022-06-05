package ma.enset.etudiantsmvc;

import ma.enset.etudiantsmvc.entities.Etudiant;
import ma.enset.etudiantsmvc.entities.Genre;
import ma.enset.etudiantsmvc.repositories.EtudiantRepository;
import ma.enset.etudiantsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class EtudiantsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtudiantsMvcApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository){
		return args ->{
			etudiantRepository.save(new Etudiant(null,"Hassan","Hassan","Hassan@gmail.com",new Date() , Genre.MASCULIN,false));
			etudiantRepository.save(new Etudiant(null,"Mohamed","Mohamed","Mohamed@gmail.com",new Date() ,Genre.MASCULIN,true));
			etudiantRepository.save(new Etudiant(null,"Yasmine","Yasmine","Yasmine@gmail.com",new Date() ,Genre.FEMININ,false));
			etudiantRepository.save(new Etudiant(null,"Hanae","Hanae","Hanae@gmail.com",new Date() ,Genre.FEMININ,true));

		};
	}

	//@Bean
	CommandLineRunner saveUsers(SecurityService securityService) {
		return args -> {
			securityService.saveUser("mohamed","1234","1234");
			securityService.saveUser("yasmine", "1234", "1234");
			securityService.saveUser("hassan", "1234", "1234");

			securityService.saveRole("USER", "");
			securityService.saveRole("ADMIN", "");

			securityService.addRoleToUser("mohamed", "USER");
			securityService.addRoleToUser("mohamed", "ADMIN");
			securityService.addRoleToUser("yasmine", "USER");
			securityService.addRoleToUser("hassan", "USER");
		};
	}
}
