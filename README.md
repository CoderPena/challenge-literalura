# Challenge Literalura

## Visão Geral

O **Challenge Literalura** é um projeto Java com Spring Boot desenvolvido como parte do desafio da Alura, cujo objetivo é consumir a API pública **Gutendex** (Projeto Gutenberg), persistir dados de livros e autores em um banco de dados relacional e permitir consultas via um menu interativo no terminal.

O sistema trabalha exclusivamente com **livros de domínio público**, organizando informações como título, autor, idioma e número de downloads.

---

## Funcionalidades

A aplicação disponibiliza um menu interativo em linha de comando com as seguintes opções:

1. Buscar livro pelo título e cadastrá-lo no banco de dados
2. Listar livros cadastrados
3. Listar autores cadastrados
4. Listar autores vivos em determinado ano
5. Listar livros por idioma
0. Sair da aplicação

Durante a busca, os dados são obtidos da API Gutendex, convertidos para objetos Java e persistidos utilizando JPA.

---

## Arquitetura do Projeto

O projeto segue uma organização em camadas, respeitando boas práticas de separação de responsabilidades:

```
src/main/java/br/com/alura/challenge_literalura
│
├── model        # Entidades JPA e DTOs
├── repository   # Repositórios Spring Data JPA
├── service      # Consumo de API externa e conversão de dados
├── ui           # Menu e interação com o usuário (CLI)
└── ChallengeLiteraluraApplication.java
```

### Camadas principais

* **model**: Contém as entidades `Livro` e `Autor`, além de classes auxiliares para mapeamento dos dados retornados pela API.
* **repository**: Interfaces JPA responsáveis pelo acesso ao banco de dados.
* **service**: Responsável pelo consumo da API Gutendex e conversão de JSON para objetos Java.
* **ui**: Implementa o menu interativo e orquestra as chamadas entre serviços e repositórios.

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot 4
* Spring Data JPA
* Maven
* PostgreSQL
* Jackson (JSON)
* API Gutendex

---

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

* Java JDK 17 ou superior
* Maven 3.8+
* PostgreSQL

---

## Configuração do Banco de Dados

Configure as propriedades de conexão com o banco PostgreSQL no arquivo `application.properties` ou `application.yml`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

O schema será criado automaticamente pelo Hibernate.

---

## Como Executar o Projeto

1. Clone o repositório:

```bash
git clone <url-do-repositorio>
```

2. Acesse o diretório do projeto:

```bash
cd challenge-literalura
```

3. Compile e execute a aplicação:

```bash
./mvnw spring-boot:run
```

4. Utilize o menu exibido no terminal para interagir com a aplicação.

---

## API Externa

* **Gutendex**: [https://gutendex.com/](https://gutendex.com/)

A API fornece acesso a livros do Projeto Gutenberg, permitindo buscas por título, autor e idioma.

---

## Observações Importantes

* A aplicação evita duplicidade de livros e autores no banco de dados.
* Apenas livros de domínio público são consumidos.
* O projeto é orientado para execução em ambiente local e uso educacional.

---

## Autor

Projeto desenvolvido por **Marcelo Pena** como parte do Challenge Java + Spring Boot da Alura.

---

## Licença

Este projeto é de uso educacional e segue as diretrizes do Projeto Gutenberg para consumo de dados públicos.

