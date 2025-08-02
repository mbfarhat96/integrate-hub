# IntegrateHub

IntegrateHub is a unified backend + frontend application that brings multiple external APIs together into one clean interface.  
It provides weather data, GitHub profile details, currency conversion, stock quotes, news search, and system health checks — all accessible through a simple dashboard.

---

## 🚀 Features

### **Backend (Spring Boot)**
- Fetch current weather by city
- Retrieve GitHub user details and repositories
- Convert between currencies
- Get live stock quotes
- Search for news across multiple sources
- System health/status endpoint
- Consistent response format
- Caching for frequently accessed data
- WebClient integrations with timeouts and fallback behavior
- Centralized exception handling

### **Frontend (Vue 3 + Vite)**
- Clean, responsive user interface
- Individual pages for all integrations
- Axios-based API service layer
- Global loading/error handling via Pinia
- Reusable components (navbar, loader, error banner, skeleton loaders)
- Mobile-friendly layout

---

## 📁 Tech Stack

### **Backend**
- Java 17
- Spring Boot
- Spring WebFlux (WebClient)
- Spring Cache
- JUnit / Mockito
- Maven

### **Frontend**
- Vue 3
- Vite
- Pinia
- Vue Router
- Axios

---

## 📦 Getting Started

### **Backend**
```bash
cd integratehub-backend
mvn spring-boot:run
```  
### **Frontend**  
```bash
cd integratehub-frontend
npm install
npm run dev
```
