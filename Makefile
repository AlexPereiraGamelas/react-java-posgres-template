.PHONY: dev prod down clean

prod:
	docker compose up --build

fe-dev:
	docker compose -f docker-compose.dev.yaml up --build -d
	cd frontend && npm install && npm run dev

fe-clean:
	cd frontend && rm -rf node_modules package-lock.json

api-rebuild:
	docker compose build api
	docker compose up -d api

api-logs:
	docker compose logs -f api

db-logs:
	docker compose logs -f db

down:
	docker compose down

clean:
	docker compose down -v