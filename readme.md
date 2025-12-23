# React + Java + Postgres Template

This repository is a minimal full‑stack MVC template with:

* **Frontend**: React + Vite
* **Backend**: Vanilla Java HTTP server (no framework)
* **Database**: PostgreSQL
* **Infra**: Docker + Docker Compose

---

## Requirements

* Docker & Docker Compose
* GNU Make
* Node.js (only required for frontend dev mode)

---

## Getting Started

````
git clone <repo>
make prod
````

--- 

## Makefile Commands

### Production (API + DB + built frontend)

```bash
make prod
```

Starts all containers using the production Docker Compose setup.

---

### Frontend Development (Vite + HMR)

```bash
make fe-dev
```

* Starts API and DB using the dev Docker Compose file
* Runs Vite locally with Hot Module Reloading

Frontend available at:

```
http://localhost:5173
```

API available at:

```
http://localhost:9090
```

API docs (swagger) available at:

```` 
http://localhost:8081
````

---

### Rebuild API Only

```bash
make api-rebuild
```

Rebuilds and restarts only the API container. Intended for backend development.

---

### Logs

```bash
make api-logs
make db-logs
```

Follow logs for API or database containers.

---

### Stop Containers

```bash
make down
```

Stops both production and dev Docker Compose stacks.

---

### Clean Volumes

```bash
make clean
```

Stops containers and removes volumes (database reset).

---

### Frontend Cleanup

```bash
make fe-clean
```

Removes `node_modules` and `package-lock.json` from the frontend.

---

## Notes

* Backend changes require rebuilding the API container
* Frontend dev mode uses a proxy to the API (`/api`)
* Swagger UI is enabled only in dev compose
* OpenAPI spec is served statically by the API

---

This template intentionally avoids heavy frameworks to keep architecture explicit and educational.
