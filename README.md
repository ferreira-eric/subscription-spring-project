# 🎬 Subscription Management Service

🚀 **Sobre o Projeto**

Este projeto faz parte de uma iniciativa de uma empresa fictícia que está entrando no mercado de **serviços de streaming**. O objetivo é gerenciar as assinaturas dos clientes, garantindo que os status sejam atualizados corretamente com base em diferentes tipos de notificações.

 **Tipos de Notificações**:
 - **SUBSCRIPTION_WAITING_FOR_PAYMENT** → Assinatura esperando pagamento.
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
- **Flyway** 💿
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

Crie um banco de dados no PostgreSQL e configure as credenciais no `application.yml` ou defina variáveis de ambiente:

```yml
  spring:
  datasource:
    url: ${POSTGRES_URL:jdbc:postgresql://localhost:5432/SEU_BANCO}
    username: ${POSTGRES_USERNAME:SEU_USER}
    password: ${POSTGRES_PASSWORD:SUA_SENHA}
    driver-class-name: org.postgresql.Driver
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
│   │   ├── application.yml  # Configurações da aplicação
│   │   ├── db
│   │   │   ├── migrations/  # Migrations usando Flyway
├── test/                      
```
---
## 📐 Modelo Entidade-Relacionamento Banco de Dados


![trabalho_final_database (1)](https://github.com/user-attachments/assets/db5f8d39-358a-49dd-95e0-026349097c3f)


---


## 📜 Licença

MIT, Apache 2.0

