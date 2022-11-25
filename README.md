
# Teste para Desenvolvedor Fullstack Júnior (Backend)

Para realizar este teste é necessário ter conhecimento nos seguintes itens:

* JAVA;
* Mysql;
* Programação Orientada a Objeto;
* Construção de Projeto em MVC;
* Maven;
* **Framework** - Spring Boot.


**Diferencial**

* Construção com **JDBCTemplate** ou **NamedParameters** em consultas nativas;
* Teste Unitário;
* Construção de Frontend em Javascript.

## Configurar o Ambiente

### Banco de Dados
Primeiro deve-se ter configurado um Servidor Mysql em seu computador, caso o seu Sistema Operacional seja
WINDOWS( [baixar o XAMPP](https://www.apachefriends.org/pt_br/index.html))  ou  LINUX/MAC( [baixar o LAMP](https://www.digitalocean.com/community/tutorials/how-to-install-linux-apache-mysql-php-lamp-stack-on-ubuntu-20-04 )). 

E com o auxílio de um software de gerenciamento de banco de dados, por exemplo o [Dbeaver](https://dbeaver.io/download/), irá criar uma nova conexão e um novo banco de dados utilizando o Script a seguir:

OBS: DEVIDO A ALGUMAS INCONSISTENCIAS ENTRE OS DOMAIN E O CREATE TABLE QUE HAVIA NO README, SEGUI COMO ESTAVA DESCRITO NOS DOMAIN, DEVIDO A ISSO PEÇO QUE GERE O BANCO DA FORMA DESCRITA ABAIXO:

PARA GERAR O BANCO DE DADOS É POSSIVEL APENAS ALTERAR NO ARQUIVO 'application.properties'
O USERNAME E SENHA E EM SEGUIDA INICIALIZAR O PROJETO QUE O BANCO SERÁ CRIADO JUNTO COM A INICIALIZAÇÃO,
COM TODAS AS TABELAS NECESSARIAS, OU DEIXO ABAIXO OS COMANDOS DE CRIAÇÃO DO BANCO:

```mysql

CREATE TABLE `service_type` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `workplace` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `finish_date` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `service_type_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtg1c2ibqafe1ic1x39w2caqif` (`service_type_id`),
  CONSTRAINT `FKtg1c2ibqafe1ic1x39w2caqif` FOREIGN KEY (`service_type_id`) REFERENCES `service_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admission_date` datetime(6) DEFAULT NULL,
  `demission_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `workplace_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKao02k0uo9bxwhf805iuu57qnt` (`workplace_id`),
  CONSTRAINT `FKao02k0uo9bxwhf805iuu57qnt` FOREIGN KEY (`workplace_id`) REFERENCES `workplace` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```

### Configurar Projeto

Após o recebimento do link do repositório "Git", é necessário clona-lo e importar o projeto utilizando a IDE ([recomendado IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)). Inicie a instalação das
dependências do projeto, utilizando o Maven; e depois configure o arquivo `application.properties`, com as informações de conexão do seu banco de dados.

```yaml
# Database
spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/nexti
spring.datasource.username = root
# spring.datasource.password = 123

spring.jpa.database = MYSQL
spring.jpa.show-sql = true


```

Tendo concluido as configurado o projeto está pronto para ser executado. Localizando a classe `TestePraticoApplication` e execute ou configure na cama de serviço na camada de aplicação.


### Visualizando o Retorno

Para este teste podemos utilizar o [Postman](https://www.postman.com/downloads/) para verificar o retorno e realizar as alterações desejadas das requisições REST do Spring Web. Contudo, seria diferencial criar uma Interface Web à sua escolha, para exibir e gerenciar os **Endpoints** da Api REST.

# Questões
O retorno esperado de acordo com a documentação a baixo; Os critérios avaliados serão baseados em organização do código, desenvolvimento lógico, código limpo e resolução dos problemas apresentados.

## API - Person

#### 1 Deve Construir o "CRUD" de Pessoa:

Deverá permitir cadastrar, atualizar e deletar um registro de alguma pessoa, o qual devem seguir os seguintes critérios de requisições:
* POST /person/create
* PUT /person/update/{id}
* DELETE /person/delete/{id} 

#### 2 Retorne todos os "Dados da Pessoa":

```http
  GET /person/all
```

| Parâmetro   | Retorno       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
|  | `List<Person>` | Retorna uma lista com todas as pessoas e suas respectivas informações|

Para essa questão, é necessário trazer todas as informações das pessoas vincualdas em sua base de dados. Como id, nome, data de admissão, demissão e informações do posto que estão faltando para este problema.

#### 3 Retorne todas as "Pessoas Ativas"

```http
  GET /person/actives
```

| Parâmetro   | Retorno       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
|  | `List<Person>` | Retorne uma lista com todas as "pessoas **Ativas**" e suas respectivas informações|

Para essa questão, é necessário **criar** o endpoint e **retorne** todas as informações das pessoas vincualdas em sua base de dados. Como id, nome, data de admissão, demissão e informações do posto.



#### 4 Retorne todas as "Pessoas Inativas"

```http
  GET /person/inactives
```

| Parâmetro   | Retorno       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
|  | `List<Person>` | Retorne uma lista com todas as "pessoas **Inativas**" e suas respectivas informações|

Para essa questão, é necessário **criar** o endpoint e **retorne** todas as informações das pessoas vincualdas em sua base de dados. Como id, nome, data de admissão, demissão e informações do posto.



#### 5 Retorne uma "Pessoa específica"

```http
  GET /person/byid/{id}
```

| Parâmetro   | Retorno       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
|  `Id : Long`   | `Person` | Retorne uma "pessoas **específica**" e suas respectivas informações|

Para essa questão, é necessário **criar** o endpoint e **retorne** todas as informações da pessoa vincualdas em sua base de dados. Como id, nome, data de admissão, demissão e informações do posto.



#### 6 Realize a troca de posto de uma pessoa

```http
  POST /person/{id}/workplacetransfer/{workplace_id}
```

| Parâmetros   | Retorno       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id : Long`, `workplace_id : Long`   | `Person` | Retorne uma pessoas **específica** e suas respectivas informações, com o novo posto configurado|

Para essa questão, é necessário **criar** o endpoint e **retornar** todas as informações da pessoa vincualdas em sua base de dados. Como id, nome, data de admissão, demissão e informações do **novo** posto.

Contudo existem regras para que a transação seja efetuada:

* Não deve permitir a troca do posto com pessoas demitidas ou que ainda não foram admitidas;
* Não deve permitir a troca do posto, com postos desativados ou que ainda não foram ativados;
* Não deve permitir a troca de posto, para um Posto com Tipo de Serviço C.

**Caso algum desses critério não seja atendido, uma mensagem de erro deverá ser retornada.**

## API - Workplace

#### 1  Construa o CRUD de Workplace

Que deverá permitir cadastrar, atualizar, deletar e listar um registro de um posto, das quais devem seguir os seguintes critérios de requisições:

* POST /workplace/create
* PUT /workplace/update/{id}
* DELETE /workplace/delete/{id} 
* GET /workplace/all


#### 2 Retorne todas as Informações de Workplace

```http
  GET /workplace/{id}
```

| Parâmetro   | Retorno       | Descrição                        |
| :---------- | :--------- | :---------------------------------- |
| `id : Long`  | `Workplace` | Retorne um posto **específico** e suas respectivas informações|

Para essa questão, é necessário **modificar** o endpoint e **retornar** todas as informações do posto e uma lista de pessoas que estejam vinculadas a esse posto, dos quais devem seguir os seguintes critérios de requisições:

* A Lista de Pessoas não deve retornar pessoas que não iniciaram ainda e que já foram demitidas.
* Postos Inativados ou Finalizados não devem apresentar nenhuma pessoa vinculada.

