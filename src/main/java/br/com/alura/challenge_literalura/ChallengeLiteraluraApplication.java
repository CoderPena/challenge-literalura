package br.com.alura.challenge_literalura;

import br.com.alura.challenge_literalura.ui.MenuService;
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
		MenuService menuService = new MenuService();
		MenuService.exibeMenu();

	}

}
