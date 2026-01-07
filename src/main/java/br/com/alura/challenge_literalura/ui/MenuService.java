package br.com.alura.challenge_literalura.ui;

import br.com.alura.challenge_literalura.model.*;
import br.com.alura.challenge_literalura.repository.AutorRepository;
import br.com.alura.challenge_literalura.repository.LivroRepository;
import br.com.alura.challenge_literalura.service.ConsumoAPI;
import br.com.alura.challenge_literalura.service.ConverteDados;

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
                    Escolha o número de sua opção:
                    
                    1 - Buscar livro pelo título e cadastrar
                    2 - Listar livros registrados
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
            } else if (opcao == 0) {
                System.out.println("Encerrando sistema...");
                return;
            } else {
                System.out.println("Opção inválida!");
            }

        }
    }

    private void buscarECadastrarLivro() {
        System.out.println("Digite o nome do livro para busca:");
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

//            // Salva o livro
//            Livro livro = new Livro(dadosLivro, autor);
//
//            // Verifica duplicidade de livro antes de salvar
//            try {
//                livroRepository.save(livro);
//                System.out.println("Livro salvo: " + livro);
//            } catch (Exception e) {
//                System.out.println("Erro ao salvar (possível duplicidade): " + e.getMessage());
//            }

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
}
