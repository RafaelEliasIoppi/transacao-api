# 💸 Transação API

![GIF de transações](https://media.giphy.com/media/3o7aD2saalBwwftBIY/giphy.gif)

API REST desenvolvida com **Java 17 + Spring Boot** para registrar, listar e gerenciar transações financeiras. Projetada para estudo, integração e evolução incremental, com arquitetura em camadas e dados mantidos em memória — **sem persistência em banco de dados**.

---

## 📊 Visão geral

- **Projeto:** Transação API  
- **Objetivo:** CRUD de transações, filtros por período/tipo e operações de consulta  
- **Arquitetura:** REST, camadas Controller → Service → Repository, DTOs e validação  
- **Status:** Produção/Estudo  
- **Persistência:** Dados em memória (sem banco de dados)

---

## 🚀 Tecnologias utilizadas

![GIF de tecnologia](https://media.giphy.com/media/l0MYt5jPR6QX5pnqM/giphy.gif)

- Java 17  
- Spring Boot  
- Maven  
- Docker  
- Render.com

---

## 🔐 Funcionalidades

- Cadastro de transações (crédito e débito)  
- Listagem com filtros (tipo, datas, descrição)  
- Consulta de saldo e estatísticas  
- Exclusão por ID  
- Respostas de erro padronizadas  
- Dados armazenados em memória durante execução

---

## 📦 Requisitos

- Java 17  
- Maven 3.8+  
- Docker (opcional)

---

## ⚙️ Como executar

```bash
git clone https://github.com/RafaelEliasIoppi/transacao-api.git
cd transacao-api
mvn clean package -DskipTests
mvn spring-boot:run
# ou
java -jar target/transacao-api.jar

🐳 Executando com Docker
Dockerfile
dockerfile
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests clean package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
Comandos
bash
docker build -t transacao-api .
docker run -p 8080:8080 --name transacao transacao-api
📘 Endpoints da API
➕ Criar transação
POST /transacoes

json
{
  "descricao": "Compra no mercado",
  "valor": 150.00,
  "tipo": "DEBITO",
  "data": "2025-01-15T10:30:00Z"
}
📄 Listar transações
GET /transacoes Parâmetros: tipo, dataInicio, dataFim, descricao, page, size

🔍 Buscar por ID
GET /transacoes/{id}

❌ Excluir por ID
DELETE /transacoes/{id}

💰 Consultar saldo
GET /transacoes/saldo

📊 Estatísticas
GET /transacoes/estatisticas

⚠️ Modelo de erro
json
{
  "timestamp": "2025-01-15T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "valor deve ser maior que zero",
  "path": "/transacoes"
}
🗂️ Estrutura do projeto
Código
src/
├─ main/
│  ├─ java/
│  │  └─ com.transacao.transacaoapi/
│  │     ├─ controller/
│  │     ├─ service/
│  │     ├─ repository/
│  │     ├─ model/
│  │     └─ dto/
│  └─ resources/
│     └─ application.properties
└─ test/
🧪 Testes
bash
mvn test
JUnit 5

Spring Boot Test

Mockito (se aplicável)

Testcontainers (opcional)

☁️ Deploy (Render)
Build Command: mvn clean package -DskipTests

Start Command: java -jar target/transacao-api.jar

Plano: Gratuito (pode hibernar após inatividade)

Variáveis: SERVER_PORT=8080

📄 Licença
Este projeto está sob a licença MIT. Sinta-se à vontade para usar, modificar e contribuir!

Feito com 💙 por Rafael Elias Ioppi
