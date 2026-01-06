package br.com.alura.challenge_literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Double numeroDownloads;

    @ManyToOne
    private Autor autor;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro, Autor autor) {
        this.titulo = dadosLivro.titulo();

        // IDIOMA INICIO
        this.idioma = null;
        if (dadosLivro != null
                && dadosLivro.idiomas() != null
                && !dadosLivro.idiomas().isEmpty()) {

            String codigo = dadosLivro.idiomas().get(0);

            if (codigo != null && !codigo.isBlank()) {
                try {
                    this.idioma = Idioma.fromCodigoApi(codigo);
                } catch (IllegalArgumentException e) {
                    this.idioma = null;
                }
            }
        }
        // IDIOMA FIM

        this.numeroDownloads = dadosLivro.numeroDownloads();
        this.autor = autor;
    }

    // Getters, Setters e toString
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + " | Autor: " + (autor != null ? autor.getNome() : "N/A") + " | Idioma: " + idioma;
    }
}

