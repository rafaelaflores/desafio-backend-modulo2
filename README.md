# DESAFIO DO MÓDULO 2 #
REST, Mysql DB

## Desafio ##

Nesta parte iremos transforma a aplicação em algo mais perto das soluções do "mundo real", utilizando API Rest e estrutras especializadas para o armazenamento dos dados.

### Requisitos e Funcionalidades Esperadas ###
- [ ] Construir APIs REST para os serviços de backend que foram desenvolvidos no Desafio do Módulo 2.
    - [ ] Alterar o backend para que os dados sejam armazenados em banco de dados, ex.: MySQL.
- [ ] Integrar a camada de linha de comando (Desafio Módulo 1) com as APIs Rest do backend.
    - [ ] Construir as interfaces para os serviços REST de inclusão, edição, exclusão e importação do arquivo de mostruário da fábrica.
- [ ] Construir imagem docker para o backend. Publicar essas imagem no [Docker Hub](https://hub.docker.com/)
    - [ ] Disponibilizar um Docker Compose com: Banco de dados, backend e frontend.
- [ ] Documentação das APIs com Swagger
- [ ] Utilizar o Spring-data
- [ ] Utilizar Hibernate
- [ ] Criar um README.md contendo as orientações para rodar a aplicação e suas dependências pela linha de comando.

### Bonus extra
- [ ] Utilizar protocolo OAuth2 para proteger a API com token de autenticação JWT.
- [ ] Realizar deploy do container da aplicação no [Heroku](https://www.heroku.com/).

### Material de Apoio ###
* [DBeaver](https://dbeaver.io/)

### Instruções para Realização do Desafio ###
1. Faça um *fork* desse repositório para a sua conta
2. Implemente a sua solução conforme solicitado
3. Compartilhe o link do projeto com o seu mentor 

***
## Critérios de Avaliação ##
* A aplicação está funcionando?
* A aplicação atende os requisitos solicitados?
    * Os serviços REST foram construídos?
    * Os verbos HTTP foram utilizados corretamente?
    * É possível incluir um novo produto pela API?
    * É possível editar os dados de um produto pela API?
    * É possível excluir um produto pela API?
    * A importação de arquivo .csv funciona corretamente pela API?
    * Os dados são armazenados corretamente no MySQL?
* Pode ser claramente entendido o que o código faz?
* O Código é fácil de entender?
* A estrutura do código é modular o suficiente?
* A solução escolhida atende aos requisitos?
* Como está o desempenho? Existe algum código que afeterá a performance?
* O código funciona como esperado?
* As classes e pacotes estão segregados por responsabilidade ou funcionalidade?
* Os métodos ou contrutores estão recebendo muitos argumentos por parâmetros?
* O código da aplicação está com nomes intuitivos de funções, variáveis ou classes?
* O escopo das variáveis de classes estão sendo utilizados de forma adequada?
* Existe cobertura de teste unitários de cenário feliz e cenário de erros para as funcionalidades desenvolvidas?
* A aplicação apresenta bugs?
* Existe um correto tratamento de erros e exceções?
* A aplicação possui logs úteis?
* Existe um readme contendo instruções de como rodar a aplicação?
* A nomenclatura das APIs está intuitiva?
* Os status HTTP para casos de falha ou sucesso estão sendo utilizando adequadamente?
* A API possui versionamento?