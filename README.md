# CRM System

A modern, enterprise-grade Customer Relationship Management (CRM) application built using **React.js**, **Spring Boot**, and **MySQL**. The system is designed with a secure, scalable architecture and supports role-based access, JWT authentication, RESTful APIs, real-time dashboard analytics, and email integration.

---

## Overview

This project demonstrates a complete full-stack application following modern software development practices. It provides separate interfaces for administrators, customers, and users while maintaining security, performance, and maintainability.

---

## Key Features

### Authentication & Authorization
- JWT-based Authentication
- Spring Security
- Role-Based Access Control (RBAC)
- Secure REST APIs
- Protected Routes

### Admin Dashboard
- Dashboard Overview
- Customer Management
- Lead Management
- User Management
- Email Management
- Analytics Dashboard

### Customer Management
- Create Customer
- Update Customer
- Customer Search
- Customer Details
- Status Management

### Lead Management
- Create Lead
- Assign Leads
- Update Lead Status
- Lead Tracking
- Lead Analytics

### Dashboard & Analytics
- KPI Cards
- Lead Statistics
- Customer Statistics
- Interactive Charts
- Real-Time Data Visualization

### Email Integration
- Gmail SMTP
- JavaMailSender
- Automated Email Notifications
- Email Activity Logging

---

# Technology Stack

## Frontend

- React.js
- JavaScript (ES6+)
- HTML5
- CSS3
- Bootstrap 5
- Axios
- React Router
- Recharts

## Backend

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- RESTful APIs
- Maven

## Database

- MySQL

## Development Tools

- Git
- GitHub
- Postman
- VS Code
- Eclipse IDE
- MySQL Workbench

---

# Architecture

```
                    React Frontend
                           │
                    REST API (Axios)
                           │
                  Spring Boot Backend
                           │
                  Spring Security + JWT
                           │
                         MySQL
```

---

# Project Structure

```
crm-system
│
├── frontend
│   ├── src
│   ├── public
│   ├── package.json
│   └── ...
│
├── backend
│   ├── src
│   ├── pom.xml
│   ├── mvnw
│   └── ...
│
└── README.md
```

---

# Getting Started

## Clone Repository

```bash
git clone https://github.com/sanket9322/crm-system.git
```

---

## Backend Setup

```bash
cd backend
```

Configure the database inside:

```
application.properties
```

Run the backend:

```bash
mvn spring-boot:run
```

---

## Frontend Setup

```bash
cd frontend
npm install
npm start
```

---

# Database Configuration

Create a MySQL database.

Example:

```
crm_db
```

Update the following properties:

```
spring.datasource.url
spring.datasource.username
spring.datasource.password
```

Import the SQL schema before running the application.

---

# Security Features

- JWT Authentication
- Password Encryption
- Spring Security
- Protected REST APIs
- Role-Based Authorization

---

# REST API Modules

- Authentication
- User Management
- Customer Management
- Lead Management
- Dashboard Analytics
- Email Services

---

# Future Enhancements

- Docker Containerization
- CI/CD Pipeline
- AWS Deployment
- Redis Caching
- Audit Logging
- File Upload Support
- PDF Report Generation
- Notification Service

---

# Screenshots

Add application screenshots here.

Example:

```
screenshots/
│
├── login.png
├── dashboard.png
├── customers.png
├── leads.png
└── analytics.png
```

---

# Learning Outcomes

This project demonstrates practical experience in:

- Full Stack Java Development
- REST API Development
- Spring Boot
- Spring Security
- JWT Authentication
- React.js
- MySQL Database Design
- CRUD Operations
- Enterprise Application Architecture
- Version Control with Git & GitHub

---

# Author

**Sanket Telgar**

Full Stack Java Developer

- GitHub: https://github.com/sanket9322
- LinkedIn: https://linkedin.com/in/sanket-telgar
- Email: sankettelgar9322@gmail.com

---

## License

This project is intended for learning, portfolio demonstration, and technical evaluation purposes.
