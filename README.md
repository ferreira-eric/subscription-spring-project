# 🎬 Subscription Management Service

🚀 **Sobre o Projeto**

Este projeto faz parte de uma iniciativa de uma empresa fictícia que está entrando no mercado de **serviços de streaming**. O objetivo é gerenciar as assinaturas dos clientes, garantindo que os status sejam atualizados corretamente com base em diferentes tipos de notificações.

 **Tipos de Notificações**:
-  **SUBSCRIPTION_PURCHASED** → Assinatura ativada após a compra.
-  **SUBSCRIPTION_CANCELED** → Assinatura cancelada pelo usuário.
-  **SUBSCRIPTION_RESTARTED** → Assinatura reativada após recuperação.

---

## 🛠️ Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando:

- **Java 17+** ☕  
- **Spring Boot 3.3.3** 🚀  
- **Spring Data JPA** 🔍  
- **PostgreSQL** 🐘  
- **Swagger OpenAPI** 📜  

---

## ⚙️ Configuração do Ambiente

### 🔹 Requisitos

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8+](https://maven.apache.org/)
- [PostgreSQL 14+](https://www.postgresql.org/)
- [Intellij IDEA](https://www.jetbrains.com/idea/) ou IDE de sua preferência
---

### 🏗️ Configuração do Banco de Dados

Crie um banco de dados no PostgreSQL e configure as credenciais no `application.properties` ou defina variáveis de ambiente:

```properties
  spring.datasource.url = jdbc:postgresql://localhost:5432/seu_banco
  spring.datasource.username = seu_usuario
  spring.datasource.password = sua_senha
  spring.jpa.hibernate.ddl-auto = update
  spring.jpa.properties.hibernate.jdbc.lob.non_contextural_creation = true
```
---

## ▶️ Executando o Projeto

1. Clone o repositório:

```sh
git clone https://github.com/ferreira-eric/subscription-spring-project.git
cd subscription-spring-project
```

2. Instale as dependências e execute o projeto:

```sh
mvn spring-boot:run
```

3. O servidor será iniciado em `http://localhost:8080`

---

## 📜 Documentação da API

A API conta com documentação Swagger disponível em:

🔗 [Swagger UI](http://localhost:8080/swagger-ui.html)  
🔗 [OpenAPI JSON](http://localhost:8080/v3/api-docs)

---

## 📂 Estrutura do Projeto

```plaintext
/src
├── main
│   ├── java/com
│   │   ├── config/             # Configurações do projeto
│   │   ├── dtos/               # Data Transfer Objects (DTOs)
│   │   ├── exceptions/         # Tratamento de exceções
│   │   ├── repository/         # Camada de persistência
│   │   ├── rest/
│   │   │   ├── api/            # Endpoints da API
│   │   │   ├── controllers/    # Controladores REST
│   │   ├── service/            # Regras de negócio
│   │   ├── utils/enums/        # Enumerações e utilitários
│   │   ├── SubscriptionApplication  # Classe principal
│   ├── resources/
│   │   ├── application.properties  # Configurações da aplicação
├── test/                      
```

---


## 📜 Licença

MIT, Apache 2.0

