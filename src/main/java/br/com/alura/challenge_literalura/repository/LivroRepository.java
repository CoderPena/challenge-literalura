package br.com.alura.challenge_literalura.repository;

import br.com.alura.challenge_literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);

    Optional<Livro> findByTituloAndAutor(String titulo, String autor);

    boolean existsByTituloAndAutor(String titulo, String autor);
}
