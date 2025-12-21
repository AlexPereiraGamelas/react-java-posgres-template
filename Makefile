.PHONY: dev prod down clean

fe-dev:
	docker compose -f docker-compose.dev.yml up --build -d
	cd frontend && npm run dev

prod:
	docker compose up --build

api-rebuild:
    docker compose build api
    docker compose up -d api

api-logs:
    docker compose logs -f api

down:
	docker compose down

clean:
	docker compose down -v