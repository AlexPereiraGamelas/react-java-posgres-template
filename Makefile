.PHONY: build run clean docker-up docker-down logs

APP_NAME=api
JAR=target/$(APP_NAME)-1.0.0.jar

build:
	mvn clean package

run: build
	java -jar $(JAR)

clean:
	mvn clean

docker-up:
	docker compose up --build

docker-down:
	docker compose down

logs:
	docker compose logs -f api