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
#ENTRYPOINT ["java","-jar","app.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]

4. Дополнительно создаем настройки для нашего приложения
Файл application.properties

spring.application.name=javaHW-05
spring.profiles.active=dev


Файл application-dev.properties

spring.datasource.password=pass1234
spring.datasource.username=root
spring.datasource.url=jdbc:mysql://localhost:3307/app
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update


Файл application-docker.properties

spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.url=jdbc:mysql://${DB_HOST}/app
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update


5. Создаем образ нашего приложения

./gradlew.bat clean build (у меня Windows)
docker build -t java-hw05:latest .
docker run --name my-app --network mydocknet -p 8080:8080 -e DB_USER=root -e DB_PASSWORD=pass1234 -e DB_HOST=mysql-DB  -d java-hw05:latest
