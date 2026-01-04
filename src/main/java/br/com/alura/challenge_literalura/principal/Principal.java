package br.com.alura.challenge_literalura.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    Escolha o número de sua opção:
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores cadastrados
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    
                    0 - Sair                    
                    
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();

        }
    }
}
