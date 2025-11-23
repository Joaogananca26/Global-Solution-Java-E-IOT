# Global-Solution-Java-e-IOT
Projeto designado a global solution da disciplina JAVA do segundo semestre do segundo ano da FIAP

--- 
🧠 Skillshift – Sistema de Autenticação e Geração de Carreira

Este projeto é uma API desenvolvida em Java + Spring Boot que permite autenticar usuários e gerar automaticamente uma profissão recomendada com base nas 10 respostas fornecidas pelo próprio usuário.
A aplicação também entrega uma trilha personalizada de aprendizagem, incluindo:

Habilidades recomendadas

Cursos sugeridos

Caminho de evolução profissional

Explicações sobre a área escolhida

O objetivo é ajudar o usuário a entender qual carreira combina mais com seu perfil e como se preparar para ela.

---

🚀 Funcionalidades da Aplicação
🔐 Autenticação & Gestão de Usuários

Criar novo usuário

Autenticar usuário (JWT)

Listar todos os usuários

Buscar usuário por ID

Atualizar parcialmente os dados do usuário

---

🎯 Recomendação de Profissão

Enviar 10 respostas e gerar automaticamente uma carreira recomendada

Consultar a carreira gerada usando o ID do usuário

---

📌 Fluxo Geral

Criar conta

Autenticar e receber um token JWT

Enviar as 10 respostas para o endpoint de geração de carreira

A API processa as respostas e retorna:

Profissão sugerida

Motivo da recomendação

Habilidades para desenvolver

Cursos e conteúdos recomendados

Trilha de aprendizado

O usuário pode consultar sua carreira a qualquer momento pelo ID

---

## 🚀 Tecnologias

- **Linguagem:** Java 21
- **Framework Web:** Spring Boot
- **Banco de Dados:** Oracle (driver `ojdbc11`)
- **ORM:** Spring Data JPA / Hibernate
- **Segurança & Autenticação:**
    - Spring Security
    - JWT com `com.auth0:java-jwt`
- **Validação:** Bean Validation (`spring-boot-starter-validation`)
- **Cache:** Spring Cache
- **Mensageria:**
    - RabbitMQ (`spring-boot-starter-amqp`)
    - Azure Storage Queue (`spring-cloud-azure-starter-storage-queue`)
- **Comunicação entre serviços:** Spring Cloud OpenFeign
- **Teste:** Spring Boot Test, Spring Security Test
- **Produtividade:** Spring DevTools
- **Code Generation:** Lombok

--- 

Link do deploy da aplicação: 
https://global-solution-java-e-iot.onrender.com/

Para matéria de Java: 
Rotas disponíveis:
https://global-solution-java-e-iot.onrender.com/users (Método POST): Criação do usuário.
https://global-solution-java-e-iot.onrender.com/login (Método POST): Pegar o token do usuário.
https://global-solution-java-e-iot.onrender.com/users (Método GET): Listar os usuários.
https://global-solution-java-e-iot.onrender.com/users/{idUsuario} (Método GET): Listar usuário por ID.
https://global-solution-java-e-iot.onrender.com/users/{idUsuario} (Método PUT): Atualização de atributos parciais do usuário.


Para matéria de IOT:
Rotas disponíveis 

Para rodar o projeto local: 
https://global-solution-java-e-iot.onrender.com/users/{idUsuario}/career (Método POST): Geração da profissão com base em 10 perguntas respondidas pelo usuário.
https://global-solution-java-e-iot.onrender.com/users/{idUsuario}/career (Método GET): Para pegar a profissão do usuário

Inicie o docker

No terminal do projeto dentro de Global-Solution-Java-E-IOT/GlobalSolutionJava/GlobalSolutionJava execute o comando: docker-compose up --build --force-recreate

Para baixar a imagem do RabbitMq

Rode o projeto.
