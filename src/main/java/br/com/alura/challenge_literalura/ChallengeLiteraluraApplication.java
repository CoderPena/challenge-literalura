package br.com.alura.challenge_literalura;

import br.com.alura.challenge_literalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		Principal principal = new Principal(repositorio);
		Principal principal = new Principal();
		principal.exibeMenu();

	}

}
