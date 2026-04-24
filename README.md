# 🌾 AgriBazzar – Full Stack E-Commerce Platform

![React](https://img.shields.io/badge/Frontend-React-blue)
![Spring Boot](https://img.shields.io/badge/Backend-SpringBoot-green)
![Docker](https://img.shields.io/badge/Container-Docker-blue)
![AWS](https://img.shields.io/badge/Cloud-AWS-orange)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange)

🔗 **Live Frontend:**  http://65.1.223.90  
🔗 **Backend API:** http://65.1.223.90:8080  
📦 **Repository:** https://github.com/YogeshSalunke-eng/Agribazar  

---

## 📌 Overview

**AgriBazzar** is a full-stack e-commerce platform for buying and selling agricultural products.  
It is built with a production-ready architecture using AWS and Docker.

- Frontend hosted on AWS S3  
- Backend deployed on AWS EC2  
- MySQL running inside Docker  
- Secure authentication using JWT (cookies)

---

## 🎯 Motivation

This project simulates a real-world scalable marketplace where:

- Sellers can list products  
- Users can browse and purchase items  
- Admins manage inventory and users  

It focuses on backend architecture, security, and real deployment practices.

---

## 🚀 Tech Stack

### Frontend
- React (Vite)
- Axios (with credentials)
- React Router
- Context API
- CSS

### Backend
- Spring Boot
- Spring Security
- JWT Authentication (Cookie-based)
- Hibernate (JPA)
- Maven

### DevOps / Cloud
- AWS EC2
- AWS S3
- Docker
- Nginx (optional)

### Database
- MySQL (Dockerized)

---

## 🔥 Features

### 🔐 Authentication
- User Registration & Login
- JWT stored in cookies
- Role-based authorization
- Password encryption

### 🛒 E-Commerce
- Product management (CRUD)
- Image upload system
- Dynamic pricing
- Order workflow 

### ⚙️ Backend
- REST APIs
- CORS configuration 
- File upload handling
- Exception handling

### ☁️ Deployment
- Dockerized backend & DB
- S3 static frontend hosting
- Scalable AWS architecture

---

## 🏗️ Architecture

```
User (Browser)
     ↓
S3 (React Frontend)
     ↓
EC2 (Spring Boot Backend - Docker)
     ↓
MySQL (Docker Container)
```

---

## 📂 Project Structure

```
agribazzar/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── security/
│   └── config/
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── context/
│   │   └── api/
│
└── README.md
```

---

## ⚙️ Setup & Installation

### Prerequisites

- Java 17+
- Node.js (v18+)
- Docker

---

### 1. Clone Repository

```bash
git clone https://github.com/YogeshSalunke-eng/Agribazar
cd agribazzar
```

---

### 2. Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

---

### 3. Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

---

### 4. Docker Setup (Optional)

```bash
docker-compose up -d
```

---

## 🔑 Environment Variables

### Backend (`application.properties`)

```
DB_URL=
DB_USERNAME=
DB_PASSWORD=

JWT_SECRET=

MAIL_USERNAME=
MAIL_PASSWORD=


change above enviornament variables with yours.
```

### Frontend (`.env`)

```
VITE_API_URL=http://<EC2_PUBLIC_IP>:8080
```

---

## 🌐 Deployment Notes

- Frontend hosted on AWS S3
- Backend hosted on EC2
- Enable CORS for frontend domain
- Use:
  ```
  withCredentials: true
  ```
- Backend should run on:
  ```
  server.address=0.0.0.0
  ```

---

## 📸 Screenshots

### 🔐 Register Page
![Register Page](screenshots/register.png)

---

### 🔐 Login Page
![Login Page](screenshots/login.png)

---

### 📊 Dashboard
![Dashboard](screenshots/dashboard.png)

---

### 📁 Create Project
![Create Project](screenshots/newproject.png)

---

### 📂 Project Details
![Project Details](screenshots/projectdetails.png)

---

### 🐞 Issue Details
![Issue Details](screenshots/issuedetails.png)

---

### 💳 Payment Page
![Payment Page](screenshots/payment.png)

---


## 🧠 Key Learnings

- AWS deployment (EC2 + S3)
- Docker containerization
- JWT authentication using cookies
- CORS handling in production
- File upload management

---

## 👨‍💻 Author

Yogesh Salunke  
Full Stack Java Developer
