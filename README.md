# LiterAlura – Catálogo de Livros (CLI)

Aplicação back-end desenvolvida em **Java com Spring Boot**, executada em modo **Command Line Interface (CLI)**, que consome a API pública **Gutendex** para criar e gerenciar um catálogo de livros persistido em banco de dados relacional.

O projeto tem como objetivo consolidar conceitos fundamentais de desenvolvimento back-end, como consumo de APIs REST, persistência de dados com JPA e organização de aplicações Spring.

---

## Visão Geral do Projeto

O LiterAlura permite ao usuário consultar livros disponíveis na API Gutendex e armazená-los localmente em um banco de dados PostgreSQL, possibilitando consultas posteriores sem a necessidade de novas requisições externas.

A interação com o sistema ocorre exclusivamente via terminal, por meio de um menu textual.

---

## Funcionalidades

A aplicação disponibiliza as seguintes opções no menu interativo:

### 1. Buscar livro pelo título
- Consome a API Gutendex
- Extrai título, autor, idioma e número de downloads
- Persiste os dados no banco de dados

### 2. Listar livros registrados
- Consulta apenas o banco de dados local
- Exibe todos os livros previamente cadastrados

### 3. Listar autores cadastrados
- Exibe:
  - Nome do autor
  - Ano de nascimento
  - Ano de falecimento
  - Livros associados

### 4. Listar autores vivos em determinado ano
- Realiza consulta baseada em critério temporal
- Retorna autores que estavam vivos no ano informado

### 5. Listar livros por idioma
- Permite filtrar livros por idioma
- Idiomas suportados:
  - Português (`pt`)
  - Inglês (`en`)
  - Espanhol (`es`)
  - Francês (`fr`)

### 0. Sair
- Finaliza a execução da aplicação

---
## Estrutura do Projeto


O projeto está organizado seguindo uma separação clara de responsabilidades:

```
br.com.alura.challenge_literalura
│
├── application
│ └── ChallengeLiteraluraApplication.java
│
├── model
│ ├── Livro.java
│ └── Autor.java
│
├── repository
│ ├── LivroRepository.java
│ └── AutorRepository.java
│
├── service
│ ├── LivroService.java
│ └── AutorService.java
│
├── client
│ └── GutendexClient.java
│
└── ui
└── MenuService.java
```

### Descrição das camadas

- **application**: classe principal do Spring Boot e ponto de entrada da aplicação CLI  
- **ui**: camada de interação com o usuário (menu no terminal)  
- **service**: regras de negócio e orquestração das operações  
- **client**: comunicação com a API externa Gutendex  
- **repository**: acesso ao banco de dados via Spring Data JPA  
- **model**: entidades JPA do domínio da aplicação  

---

## Tecnologias Utilizadas

- Java  
- Spring Boot  
- Spring Data JPA  
- Maven  
- PostgreSQL  
- API Gutendex  

---

## Arquitetura e Execução

- Aplicação Spring Boot executada em modo **CLI**
- Inicialização da lógica principal via `CommandLineRunner`
- Menu textual encapsulado na classe `MenuService`
- Persistência de dados utilizando JPA e banco relacional
- A API externa é utilizada apenas na busca inicial dos livros

---

## Pré-requisitos

- Java 17 ou superior  
- Maven  
- PostgreSQL  
- Terminal ou IDE (IntelliJ IDEA, Eclipse ou VS Code)

---

## Configuração do Banco de Dados

No arquivo `application.properties`, configure os parâmetros de conexão:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.main.web-application-type=none

---


## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Maven**
- **PostgreSQL**
- **API Gutendex**

---

## Arquitetura e Execução

- Aplicação Spring Boot executada em modo **CLI**
- Lógica principal iniciada por meio de `CommandLineRunner`
- Persistência de dados utilizando JPA e banco relacional
- A API externa é utilizada apenas no momento da busca inicial dos livros

---

## Pré-requisitos

- Java 17 ou superior
- Maven
- PostgreSQL
- Terminal ou IDE (IntelliJ, Eclipse, VS Code)

---

Execução do Projeto
Gerar o JAR
mvn clean package

Executar a aplicação
java -jar target/literalura-0.0.1-SNAPSHOT.jar

API Utilizada

Gutendex – API pública de obras literárias
https://gutendex.com/

Objetivo Educacional

Este projeto foi desenvolvido como parte de um desafio prático com foco em:

Consumo de APIs REST

Manipulação e transformação de dados

Modelagem de entidades

Persistência em banco de dados relacional

Consultas com Spring Data JPA

Organização de aplicações back-end em camadas

Uso do Spring Boot em aplicações de linha de comando

