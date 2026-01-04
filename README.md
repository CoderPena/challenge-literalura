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

## Configuração do Banco de Dados

No arquivo `application.properties`, configure os parâmetros de conexão:

```properties
spring.datasource.url=jdbc:postgresql://localhost/literalura
spring.datasource.username=postgres
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.main.web-application-type=none

