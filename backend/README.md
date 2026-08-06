# CRM Backend 🚀

A robust RESTful backend for a full-stack CRM system built with **Spring Boot 3** and **Java 21**. Handles authentication, lead management, support tickets, feedback, and role-based access for Admin, User, and Customer roles.

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.4.12 |
| Security | Spring Security + JWT (jjwt 0.11.5) |
| Database | MySQL |
| ORM | Spring Data JPA (Hibernate) |
| Mail | Spring Boot Mail |
| Build Tool | Maven |
| Utilities | Lombok, Jakarta Validation |

---

## ✨ Features

- **JWT Authentication** — Secure login with access tokens; stateless session management
- **Role-Based Access Control** — Three roles: `ADMIN`, `USER`, `CUSTOMER` with separate dashboards
- **Lead Management** — Create, update, track, and assign leads across the pipeline
- **Ticket System** — Support ticket creation, assignment, and resolution workflow
- **Feedback Module** — Customer feedback submission and admin review
- **Email Service** — Automated email notifications via Spring Mail
- **Input Validation** — Jakarta Bean Validation on all incoming request bodies

---

## 📁 Project Structure

```
src/
└── main/
    ├── java/com/example/backend/
    │   ├── config/          # Security config, JWT filter, CORS
    │   ├── controller/      # REST controllers (Auth, Lead, Ticket, Feedback)
    │   ├── dto/             # Request/Response DTOs
    │   ├── entity/          # JPA entities
    │   ├── repository/      # Spring Data JPA repositories
    │   ├── service/         # Business logic layer
    │   └── BackendApplication.java
    └── resources/
        └── application.properties
```

---

## ⚙️ Prerequisites

- Java 21+
- Maven 3.8+
- MySQL 8.0+

---

## 🔧 Setup & Configuration

### 1. Clone the repository

```bash
git clone https://github.com/sanket9322/crm-backend.git
cd crm-backend
```

### 2. Create the MySQL database

```sql
CREATE DATABASE crm_db;
```

### 3. Configure `application.properties`

Edit `src/main/resources/application.properties`:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/crm_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000

# Mail (optional)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The server starts at `http://localhost:8080`.

---

## 🔑 API Overview

### Auth Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and receive JWT |

### Lead Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/leads` | Get all leads |
| POST | `/api/leads` | Create a new lead |
| PUT | `/api/leads/{id}` | Update lead |
| DELETE | `/api/leads/{id}` | Delete lead |

### Ticket Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/tickets` | Get all tickets |
| POST | `/api/tickets` | Create a ticket |
| PUT | `/api/tickets/{id}` | Update ticket status |

### Feedback Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/feedback` | Get all feedback |
| POST | `/api/feedback` | Submit feedback |

> **Note:** All endpoints except `/api/auth/**` require a valid `Authorization: Bearer <token>` header.

---

## 🔒 Security

JWT tokens are generated on login and must be included in the `Authorization` header for all protected routes. Role-based method security ensures users only access resources appropriate to their role.

---

## 🤝 Related Repository

Frontend: [crm-frontend](https://github.com/sanket9322/crm-frontend) — React.js UI for this backend.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
