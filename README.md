Transação API
API REST em Java com Spring Boot para registrar, listar e gerenciar transações financeiras. Projetada para estudo, integração e evolução incremental, com arquitetura em camadas e persistência em H2.

Substitua os campos marcados com TODO pelos valores do seu projeto.

Visão geral
Projeto: Transação API

Objetivo: CRUD de transações, filtros por período/tipo e operações de consulta

Arquitetura: REST, camadas Controller → Service → Repository, DTOs e validação

Banco: H2 em modo arquivo

Deploy: Render.com

Status: Produção/Estudo

Tecnologias
Java 17

Spring Boot

Maven

H2 Database

Docker

Render.com

Funcionalidades
Cadastro de transações (crédito e débito)

Listagem com filtros (tipo, datas, descrição) [se aplicável]

Consulta de saldo e estatísticas [se aplicável]

Exclusão por ID

Console H2 em /h2-console

Respostas de erro padronizadas

Requisitos
Java 17

Maven 3.8+

Docker (opcional)

Configuração
Crie/ajuste application.properties:

properties
# Porta
server.port=${SERVER_PORT:8080}

# H2
spring.datasource.url=jdbc:h2:file:./data/transacoes
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
Se usar perfis:

properties
spring.profiles.active=dev
Como executar
bash
# Clonar
git clone https://github.com/RafaelEliasIoppi/transacao-api.git
cd transacao-api

# Build
mvn clean package -DskipTests

# Rodar
mvn spring-boot:run
# ou
java -jar target/TRANSACAO-API-JAR-AQUI.jar  # TODO: substitua pelo nome real
Aplicação: http://localhost:8080

Docker
Dockerfile (multi-stage):

dockerfile
# Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests clean package

# Run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
Comandos:

bash
docker build -t transacao-api .
docker run -p 8080:8080 --name transacao transacao-api
Endpoints da API
Ajuste os caminhos conforme seus controllers. Abaixo é um modelo comum.

Criar transação

Método: POST

Rota: /transacoes

Body:

json
{
  "descricao": "Compra no mercado",
  "valor": 150.00,
  "tipo": "DEBITO",
  "data": "2025-01-15T10:30:00Z"  // TODO: se houver campo data
}
Respostas: 201 Created | 400 Bad Request

Listar transações

Método: GET

Rota: /transacoes

Query params: tipo, dataInicio, dataFim, descricao, page, size // TODO: ajuste

Resposta: 200 OK

Buscar por ID

Método: GET

Rota: /transacoes/{id}

Respostas: 200 OK | 404 Not Found

Excluir por ID

Método: DELETE

Rota: /transacoes/{id}

Respostas: 204 No Content | 404 Not Found

Saldo/estatísticas [opcional]

GET /transacoes/saldo

GET /transacoes/estatisticas

Modelo de erro:

json
{
  "timestamp": "2025-01-15T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "valor deve ser maior que zero",
  "path": "/transacoes"
}
Estrutura do projeto
Substitua com seus pacotes reais.

Código
src/
├─ main/
│  ├─ java/
│  │  └─ com.transacao.transacaoapi/        # TODO: ajuste se necessário
│  │     ├─ controller/
│  │     ├─ service/
│  │     ├─ repository/
│  │     ├─ model/                          # ou entity
│  │     └─ dto/
│  └─ resources/
│     ├─ application.properties
│     └─ data.sql                           # opcional
└─ test/
Entidade Transacao (exemplo):

java
// package com.transacao.transacaoapi.model; // TODO

public class Transacao {
  private Long id;
  private String descricao;
  private java.math.BigDecimal valor;
  private String tipo; // CREDITO | DEBITO (ou enum)
  private java.time.Instant data; // TODO: se houver
}
Testes
bash
mvn test
JUnit 5 + Spring Boot Test

Mockito para serviços [se houver]

Testcontainers (opcional) para integração

Deploy (Render)
Build Command: mvn clean package -DskipTests

Start Command: java -jar target/TRANSACAO-API-JAR-AQUI.jar # TODO

Plano: Free (pode hibernar após inatividade)

Variáveis: SERVER_PORT=8080 (ou porta usada)

Licença
MIT. Use, modifique e contribua livremente.

Se você me enviar:

pom.xml

application.properties/yml

nomes de pacotes

classes Controller e Model

eu te devolvo um README 100% fiel ao repositório, sem nenhum placeholder.
