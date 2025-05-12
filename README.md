
# Vibe Microservice

Vibe is a microservice responsible for managing digital identity profiles (Vibes) within the EasyLink system. EasyLink is a platform for passwordless authentication based on personal associations.

The microservice provides APIs for creating, storing, and retrieving Vibe profiles that users can use for login, sharing, and managing their digital presence.

---

## 🎯 Design Approach

This project follows **Clean Architecture** and **SOLID** principles to ensure flexibility, testability, and resilience to change. The architecture is designed with scalability in mind and allows future extraction of components into standalone services (e.g., association verification or public Vibe generation).

---

## 🧱 Architecture Structure

### Main Modules:
- **domain** – contains business entities, value objects, and interfaces. This is a stable, independent component. It **has no dependencies**, but other layers depend on it.
- **application** – implements business logic (use cases), relies only on `domain` interfaces, and is independent from infrastructure or frameworks.
- **infrastructure** – contains implementations: database access, logging, etc.
- **web** – the controller layer that exposes the REST API.

---

## ⚙️ Stability Metrics (based on Robert C. Martin)

| Module         | Fan-out (FO) | Fan-in (FI) | Instability `I = FO / (FO + FI)` | Notes                                 |
|----------------|--------------|-------------|----------------------------------|----------------------------------------|
| `domain`       | 0            | 1+          | 0.00                             | Most stable and abstract layer         |
| `application`  | 1            | 1           | 0.50                             | Mediator between API and business logic|
| `infrastructure` | 1          | 0           | 1.00                             | Depends on `domain`, easily replaceable|
| `web`          | 1            | 0           | 1.00                             | REST interface, depends on use cases   |

The dependencies always point inward — toward the most stable and abstract layers — following the **Dependency Rule** of Clean Architecture.

---

## 🛠️ Tech Stack

- Java 21  
- Spring Boot 3  
- Spring Data JPA  
- PostgreSQL  
- Gradle  

---

## 📌 Current Functionality

| Method | Endpoint             | Description                           |
|--------|----------------------|---------------------------------------|
| POST   | `/vibes`             | Create a new Vibe profile             |
| GET    | `/vibes/{id}`        | Get a profile by ID                   |
| GET    | `/vibes/by-user`     | Get all Vibes for a given user        |
| POST   | `/auth/verify`       | Verify personal association (stub)    |

---

## 🧩 Extensibility

The architecture enables:
- Scaling components independently;
- Adding new interfaces without changing business logic;
- Extracting functional parts into standalone services without breaking consistency.

---

## 👨‍💻 Author

**Andrey Chemchiev — Backend Developer with 17+ years of hands-on programming experience in enterprise systems (finance, retail, logistics).
Most of my past work was done using a different programming language, but I’m now focused on Java and Spring.
This diverse background gives me a solid foundation in architecture, backend development, and real-world business logic.

---
