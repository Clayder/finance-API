  

# ** :fist_right::fist_left: Objetivo ** 

O projeto está sendo desenvolvido utilizando como base o curso **Spring Boot, Hibernate, REST, Ionic, JWT, S3, MySQL, MongoDB (https://www.udemy.com/course/spring-boot-ionic/ ).** Esse é um excelente curso, indicado para quem está começando a aprender e também para quem já tem conhecimentos em Spring Boot.

**Dica:** No decorrer do curso o professor desenvolve uma API para delivery, como eu já possuo conhecimentos básicos em Spring Boot, achei melhor não reproduzir o mesmo sistema, ou seja, enquanto o professor desenvolvia a API para sistema de pedidos online eu desenvolvi uma API para realizar o gerenciamento de cartão de crédito e faturas, com isso, o meu aprendizado foi muito melhor.

Esse é um projeto apenas para estudo que vai permanecer em constante desenvolvimento, o objetivo principal é obter mais conhecimentos em:

-   :heavy_check_mark: Spring Boot
-   :heavy_check_mark: Docker
-   :heavy_check_mark: API Rest
-   :heavy_check_mark: Swagger
-  :heavy_check_mark:  Postman
-   :heavy_check_mark: MySQL
-   :heavy_check_mark: Hibernate
-   :heavy_check_mark: Git
-   :heavy_check_mark: JWT - JSON Web Tokens
-   :heavy_check_mark: Spring Security
-   :heavy_check_mark: Java
-   :heavy_multiplication_x: TDD
-   :heavy_multiplication_x: RabbitMQ
-   :heavy_multiplication_x: Jenkins
    
# **:arrow_down: Instalação do projeto**

1.  Instalar e configurar o java 8 .
2.  Instalar e configurar o MySql.
3.  Clonar esse repositório: **_git clone git@github.com:Clayder/finance-API.git_**
4.  Acessar a pasta do projeto e executar o Maven
5.  Configurar o arquivo **_src/main/resources/application.properties_** inserindo os dados do banco de dados.
6.  Executar o Spring App
7.  Realizar as configurações abaixo do Postman
    
## **Utilização do banco de dados via docker**

1.  Instalar o docker: [https://www.digitalocean.com/community/tutorials/como-instalar-e-usar-o-docker-no-ubuntu-18-04-pt](https://www.digitalocean.com/community/tutorials/como-instalar-e-usar-o-docker-no-ubuntu-18-04-pt)
2.  Instalar o docker compose: [https://www.digitalocean.com/community/tutorials/how-to-install-docker-compose-on-ubuntu-18-04-pt](https://www.digitalocean.com/community/tutorials/how-to-install-docker-compose-on-ubuntu-18-04-pt)
3.  Acessar o projeto via terminal
4.  Executar: **_docker-compose up db_**
    

# **Consumo da API via Postman**

Como importar projetos no Postman ? [Clique aqui !!](https://nfe.io/docs/comum/postman/)

# **:open_file_folder: Estrutura do projeto**

### Camadas

1.  **Resources:** Controladores rests.
    
2.  **Services:** Camada que realiza a integração com o Repository, ou seja, essa camada implementa a interface Repository.
    
3.  **Repositories:** Interface de comunicação para o acesso aos dados.
    
4.  **Domain:** Onde fica armazenado os dados da tabela da classe.
    
5.  **DTO:** Camada responsável por manipular o JSON que será exibido no response.
    
### Sub-camadas

1.  **Resource/Exception:**

	1.1. **classe ResourceExceptionHandler:** Tem a função de interceptar e tratar as exceções lançadas nos resources (controllers). Essa classe possui uma annotation chamada **@ControllerAdvice**, ele que intercepta as exceções geradas por métodos anotados com **@RequestMapping.** Mais informações:[https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f](https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f)
    
3.  **Services/Exception:** Possui as classes que criam exceções personalizadas.
  
# **:spiral_notepad: Documentação da API**

A documentação da API foi feita utilizando o Swagger e toda essa geração de documentação foi feita utilizando a biblioteca **SpringFox**. Os links abaixo possuem excelentes explicações de como utilizar essa biblioteca com Spring Boot.

1.  Site oficial da biblioteca: [http://springfox.github.io/springfox/](http://springfox.github.io/springfox)
2.  Tutorial: [https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/](https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/)
3.  Exemplos de utilização:

    3.1.  [https://github.com/springfox/springfox/tree/master/springfox-petstore/src/main/java/springfox/petstore](https://github.com/springfox/springfox/tree/master/springfox-petstore/src/main/java/springfox/petstore)
    
    3.2.  [https://github.com/springfox/springfox](https://github.com/springfox/springfox)
    

Para acessar a documentação do projeto é só inicializar o servidor e acessar [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#)

  

![](https://github.com/Clayder/finance-API/blob/master/public/img/swagger/swagger.png?raw=true)

  

  

![](https://github.com/Clayder/finance-API/blob/master/public/img/swagger/utilizando-swagger.gif?raw=true)

  

# **:closed_lock_with_key: JSON Web Tokens**

A segurança do projeto foi feito utilizando o **Spring Security**, através dessa biblioteca é possível gerar o JWT, para realizar a autenticação na API.

**O que é JWT:** [https://medium.com/tableless/entendendo-tokens-jwt-json-web-token-413c6d1397f6](https://medium.com/tableless/entendendo-tokens-jwt-json-web-token-413c6d1397f6)

Para realizar a geração do JWT, tem que ser feita uma requisição POST [http://localhost:8080/login](http://localhost:8080/login) , passando o login e senha do usuário.

    {
        "email": "admin@gmail.com",
        "password": "12345678"
    }

Se ocorrer tudo ok, a API vai retornar status code 200 e com o JWT no header (Authorization) do response .

![](https://github.com/Clayder/finance-API/blob/master/public/img/jwt/gerando-jwt.gif?raw=true)

É necessário passar o Authorization em todos os outros endpoints da API (menos no POST /login)

![](https://github.com/Clayder/finance-API/blob/master/public/img/jwt/utilizando-jwt.gif?raw=true)

Através do site [https://jwt.io/](https://jwt.io) é possível entender melhor o conteúdo desse token JWT e até mesmo visualizar o seu tempo de expiração (**exp**) no **PAYLOAD:DATA.**

![](https://github.com/Clayder/finance-API/blob/master/public/img/jwt/jwtio.gif?raw=true)
