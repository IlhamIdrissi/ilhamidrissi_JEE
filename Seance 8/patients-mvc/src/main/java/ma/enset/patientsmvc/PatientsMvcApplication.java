package ma.enset.patientsmvc;

import ch.qos.logback.core.net.SyslogOutputStream;
import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import ma.enset.patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;



@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args ->{
			patientRepository.save(
					new Patient(null,"Hassan",new Date() ,false,122));
			patientRepository.save(
					new Patient(null,"Mohamed",new Date() ,true,321));
			patientRepository.save(
					new Patient(null,"Yasmine",new Date() ,true,165));
			patientRepository.save(
					new Patient(null,"Hanae",new Date() ,false,132));

			patientRepository.findAll().forEach(p->{
				System.out.println(p.getNom());
			});
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
