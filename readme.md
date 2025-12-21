# Vanilla Java MVC API Template

A lightweight, **framework-free MVC API template** built with plain Java and Docker.  
Designed to serve a **JSON-only API** intended to be consumed by a **Vite + React frontend**.

This project intentionally avoids frameworks (Spring, Jakarta, etc.) in order to provide:

- Full control over application flow
- Minimal magic and hidden behavior
- Clear separation of concerns
- Easy learning, debugging, and extension

---

## Stack

- **Java 18** (Zulu / Temurin)
- **PostgreSQL 16**
- **JDBC** with **HikariCP** connection pooling
- **com.sun.net.httpserver** (built-in HTTP server)
- **Docker & Docker Compose**
- **Maven** (build & dependency management)

---

## Project Characteristics

- JSON-only API (no server-side rendering)
- Explicit MVC-style layering
- Plain SQL (no ORM)
- Docker-first development workflow
- Single executable JAR output

---

## Requirements

You need the following installed locally:

- Java **18**
- Maven **3.9+**
- Docker + Docker Compose
- `make`

Verify your setup:

```bash
java -version
mvn -version
docker --version
docker compose version
make --version
```
---
## Running the Application
### Development Mode
Run API + DB in Docker, frontend with Vite (HMR):
```bash
make fe-dev
```
### Production Mode
Run all services fully in Docker (frontend served by Nginx):
```bash
make prod
```
### Backend Helpers
Rebuild and restart the API after code changes:
```bash
make api-rebuild
```
View API Logs:
```bash
make api-logs
```
### Stopping and Cleaning
Stop all running containers:
```bash
make down
```
Stop and remove containers + volumes (DB reset):
```bash
make clean
```
