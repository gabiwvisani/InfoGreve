# InfoGreves

Aplicação web para gerenciamento de greves, permitindo o cadastro de um registro de greve, edição, adição dos dados finais da greve, exclusão e consulta.

## Tecnologias Utilizadas

- **Backend**: Spring Boot, PostgreSQL, Liquibase
- **Frontend**: ReactJS, Bootstrap, React Bootstrap, React Router DOM, Axios, React Toastify

## Como rodar o projeto

### Backend

Siga as instruções no arquivo `README.md` dentro do diretório do backend.

### Frontend

O front-end foi construído usando ReactJS e depende de Node.js e npm/yarn para rodar.

#### Estrutura do Projeto

src/: Contém todos os componentes e lógica da aplicação.
components/: Contém os componentes React usados na aplicação, como Navbar, GreveForm, GreveList, GreveFimForm e GreveDetail.
api/: Arquivo onde estão as funções responsáveis pela comunicação com a API do backend.
App.js: Arquivo principal da aplicação, onde as rotas e o layout principal são definidos.

##### Descrição dos Componentes

Navbar: Barra de navegação simples que permite acessar as diferentes páginas da aplicação, como a listagem de greves e o formulário para adicionar greves.

GreveForm: Componente responsável por renderizar o formulário para adicionar ou editar uma greve. Ele faz chamadas à API para criar ou atualizar os dados.

GreveFimForm: Formulário dedicado à atualização dos dados de encerramento da greve, como a data de término e se a greve foi bem-sucedida.

GreveList: Exibe uma lista de todas as greves registradas, com opções para editar, excluir ou adicionar informações sobre o final da greve.

GreveDetail: Exibe detalhes de uma greve específica, incluindo informações como data de início, data de fim, motivo, categoria, entre outros.

#### Pacotes Externos

axios: Utilizado para realizar as requisições HTTP para o backend.
react-toastify: Para exibir mensagens de notificação no sistema.
react-bootstrap: Fornece componentes pré-estilizados para a construção da interface.

### Dependências

Antes de rodar o projeto, instale as dependências:
Instar Docker
Instalar Java 17
Instalar npm
bash
npm install

### Como rodar o projeto
Com o docker já instalado, rodar o docker-compose que está dentro do backend.
Depois, rodar a aplicação java.
Em seguida, rodar o front-end (npm start).
