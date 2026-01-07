tag=latest

all: server

server: dummy
	./gradlew clean build -x test

run:
	./gradlew bootRun

test: dummy
	./gradlew test

dockerbuild:
	docker buildx build --platform linux/amd64 -t kobums/gymspring:$(tag) .

docker: server dockerbuild

dockerrun:
	docker run --platform linux/amd64 -d --name="gymspring" -p 8004:8004 kobums/gymspring:$(tag)

push: docker
	docker push kobums/gymspring:$(tag)

compose-up:
	docker-compose up -d --build

compose-down:
	docker-compose down

compose-logs:
	docker-compose logs -f

clean:
	./gradlew clean
	docker stop gymspring || true
	docker rm gymspring || true

dummy: