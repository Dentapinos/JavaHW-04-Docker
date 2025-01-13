1. Создаем сеть через которую будут общатся наши контейнеры

docker network create mydocknet

2. Создаем контейнер базы данных

docker pull mysql:8.2.0
docker run -p 3307:3306 --name mysql-DB --net mydocknet -e MYSQL_ROOT_PASSWORD=pass1234 -e MYSQL_DATABASE=app -d mysql:8.2.0
docker exec -it mysql-DB mysql -u root -D app -p

CREATE TABLE IF NOT EXISTS users (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
age INT
);

INSERT INTO users (name, age) VALUES
('Denis', 30),
('Maria', 20),
('Gena', 8);

3. Создаем докер файл Dockerfile:
FROM openjdk:21
WORKDIR /app
ARG JAR_FILE=build/libs/javaHW-05-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

4. Создаем образ нашего приложения

./gradlew.bat clean build (у меня Windows)
docker build -t java-hw05:latest .
docker run --name container-hw-05 --network mydocknet -p 8081:8080 -e DB_HOST=mysql-DB -e DB_PORT=3307 -d java-hw05:latest





У меня контейнер создается и ничего не работает