### InfoGreves Back-end

#### Descrição do Projeto

O InfoGreves é uma aplicação desenvolvida para gerenciar informações relacionadas a greves. Ela permite o cadastro, busca, atualização e exclusão de registros de greves, incluindo dados como data de início e término, motivo, sindicato responsável, número de trabalhadores envolvidos, e se a greve foi bem-sucedida ou não.

A aplicação é desenvolvida utilizando Spring Boot, com persistência de dados em um banco PostgreSQL. Para controle de versões e gerenciamento do banco de dados, é utilizado Liquibase. Além disso, a aplicação conta com integração com OpenAPI para documentação e teste de endpoints.

#### Tecnologias Utilizadas

* Java 17
* Spring Boot
* PostgreSQL
* Liquibase
* OpenAPI
* Docker

#### Funcionalidades

* Cadastrar Greve: Registra uma nova greve com dados como data de início, motivo, sindicato responsável, etc.
* Buscar Greves: Retorna uma lista de todas as greves cadastradas.
* Buscar Greve por ID: Retorna informações detalhadas de uma greve específica com base no seu ID.
* Atualizar Greve: Permite modificar os dados de uma greve já cadastrada.
* Adicionar Dados Finais à Greve: Registra o desfecho da greve, incluindo se teve sucesso e os acordos alcançados.
* Deletar Greve: Remove o registro de uma greve.

#### Configuração e Instalação
##### Pré-requisitos

* Java 17
* Docker
* Maven
* Passos de Instalação
* Clone o repositório:

bash
git clone https://github.com/gabiwvisani/InfoGreve.git
cd InfoGreves/Backend/InfoGreves

#### Configuração do Banco de Dados:

O banco de dados é configurado automaticamente usando Docker e Liquibase. Certifique-se de ter o Docker instalado e executando.

##### Rodar o Banco de Dados via Docker:

docker-compose up -d
Isso inicializará o banco de dados PostgreSQL e executará os scripts de migração do Liquibase.

#### Rodar a Aplicação:

bash
mvn spring-boot:run
A aplicação estará disponível em http://localhost:9080.

#### Documentação da API
A documentação dos endpoints da API pode ser acessada via OpenAPI em:

bash
http://localhost:9080/swagger-ui.html

#### Configuração CORS
No arquivo WebConfig, o CORS está habilitado para permitir solicitações de http://localhost:3000. O que está sendo usado no front-end.
