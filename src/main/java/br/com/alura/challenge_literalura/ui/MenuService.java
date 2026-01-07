package br.com.alura.challenge_literalura.ui;

import br.com.alura.challenge_literalura.model.*;
import br.com.alura.challenge_literalura.repository.AutorRepository;
import br.com.alura.challenge_literalura.repository.LivroRepository;
import br.com.alura.challenge_literalura.service.ConsumoAPI;
import br.com.alura.challenge_literalura.service.ConverteDados;

import java.util.List;
import java.util.Scanner;

public class MenuService {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public MenuService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ##############################################
                    Informe a opção desejada:
                    ##############################################
                    1 - Buscar livro pelo título e cadastrar
                    2 - Listar livros cadastrados
                    3 - Listar autores cadastrados
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    
                    0 - Sair
                    
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine(); // Limpa buffer

            if (opcao == 1) {
                buscarECadastrarLivro();
            } else if (opcao == 2) {
                listarLivrosCadastrados();
            } else if (opcao == 3) {
                listarAutoresCadastrados();
            } else if (opcao == 4) {
                listarAutoresVivosEmDeterminadoAno();
            } else if (opcao == 5) {
                listarLivrosPorIdioma();
            } else if (opcao == 0) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inexistente no menu!");
            }
        }
    }

    private void buscarECadastrarLivro() {
        System.out.println("Informe o título do livro para pesquisa:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        var dados = conversor.obterDados(json, DadosGutendex.class);

        if (dados.resultados() != null && !dados.resultados().isEmpty()) {

            // Pega o primeiro resultado
            DadosLivro dadosLivro = dados.resultados().get(0);

            // Verifica se o autor já existe no banco
            DadosAutor dadosAutor = dadosLivro.autores().get(0);
            Autor autor = autorRepository.findByNome(dadosAutor.nome())
                    .orElseGet(() -> autorRepository.save(new Autor(dadosAutor)));

            // Verifica se já existe livro com mesmo título e autor
            if (livroRepository.existsByTituloAndAutor(dadosLivro.titulo(), autor)) {
                System.out.println("Livro já cadastrado para este autor.");
                return;
            }

            // Salva o livro
            Livro livro = new Livro(dadosLivro, autor);
            livroRepository.save(livro);
            System.out.println("Livro salvo: " + livro);

        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void listarLivrosCadastrados() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado até o momento.");
            return;
        }

        System.out.println("\nLivros cadastrados no sistema:\n");

        livros.forEach(livro -> {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor().getNome());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Downloads: " + livro.getNumeroDownloads());
            System.out.println("-----------------------------------");
        });
    }

    private void listarAutoresCadastrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado no sistema.");
            return;
        }

        System.out.println("\nAutores cadastrados:\n");

        autores.forEach(autor -> {
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Ano de nascimento: " + autor.getAnoNascimento());

            if (autor.getAnoFalecimento() != null) {
                System.out.println("Ano de falecimento: " + autor.getAnoFalecimento());
            } else {
                System.out.println("Autor ainda vivo.");
            }

            System.out.println("-----------------------------------");
        });
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.print("Informe o ano para consulta: ");
        int anoConsulta = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = autorRepository.findAll();

        List<Autor> autoresVivos = autores.stream()
                .filter(autor ->
                        autor.getAnoNascimento() != null &&
                                autor.getAnoNascimento() <= anoConsulta &&
                                (autor.getAnoFalecimento() == null ||
                                        autor.getAnoFalecimento() > anoConsulta)
                )
                .toList();

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado para o ano informado.");
            return;
        }

        System.out.println("\nAutores vivos no ano " + anoConsulta + ":\n");

        autoresVivos.forEach(autor -> {
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Ano de nascimento: " + autor.getAnoNascimento());

            if (autor.getAnoFalecimento() != null) {
                System.out.println("Ano de falecimento: " + autor.getAnoFalecimento());
            } else {
                System.out.println("Autor ainda vivo.");
            }

            System.out.println("-----------------------------------");
        });
    }

    private void listarLivrosPorIdioma() {
        System.out.print("Informe o idioma para consulta (pt, en, es, fr): ");
        String entrada = leitura.nextLine().trim();

        Idioma idioma;
        try {
            idioma = Idioma.fromCodigoApi(entrada);
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma inválido. Utilize pt, en, es ou fr.");
            return;
        }

        List<Livro> livros = livroRepository.findAll();

        List<Livro> livrosPorIdioma = livros.stream()
                .filter(livro ->
                        livro.getIdioma() != null &&
                                livro.getIdioma() == idioma
                )
                .toList();

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma: " + idioma);
            return;
        }

        System.out.println(
                "\nLivros cadastrados em " + idioma.getDescricao() + ":\n"
        );

        livrosPorIdioma.forEach(livro -> {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor().getNome());
            System.out.println("Downloads: " + livro.getNumeroDownloads());
            System.out.println("-----------------------------------");
        });
    }


}
