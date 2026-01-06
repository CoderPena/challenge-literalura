package br.com.alura.challenge_literalura;

import br.com.alura.challenge_literalura.repository.AutorRepository;
import br.com.alura.challenge_literalura.repository.LivroRepository;
import br.com.alura.challenge_literalura.ui.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository repositorioLivro;
	@Autowired
	private AutorRepository repositorioAutor;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		MenuService menu = new MenuService(repositorioLivro, repositorioAutor);
		menu.exibeMenu();
	}

}
