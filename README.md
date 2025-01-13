
# Hi, I'm Dentapinos! 👋


# Домашнее задание №5 - изучение Docker

## Создание контейнера mysql, базы данных, таблицы и заполнение ее данными

<span style="color:green">docker pull mysql:8.2.0 </span>- Скачал mysql image версии 8.2.0

<span style="color:green">docker images</span> - Вывел все images убедился что image mysql скачан успешно

<span style="color:green">docker network create mydocknet</span> - создаем сеть с именем mydocknet для того что бы контейнеры могли общаться между собой по их именам

<span style="color:green">docker run -p 3307:3306 --name mysql-DB --net mydocknet -e MYSQL_ROOT_PASSWORD=pass1234 -e MYSQL_DATABASE=app -d mysql:8.2.0</span> 
3307 внешний порт, 3306 внутренний порт msql, mysql-DB - имя контейнера, MYSQL_ROOT_PASSWORD - пароль от базы данных, MYSQL_DATABASE - имя базы данных, mysql:8.2.0 - image который развернем в контейнере

<span style="color:green">docker exec -it mysql-DB mysql -u root -D app -p</span>
exec -it - вход в mysql в интерактивном режиме, -u root - имя пользователя, -D app - имя базы данных, -p - пароль, но он пуст для безопасности, его вводим следующим шагом при запросе Enter password:

Создаем таблицу users с полями id, name, age:<br>
<span style="color:green">
CREATE TABLE IF NOT EXISTS users (<br>
id INT PRIMARY KEY AUTO_INCREMENT,<br>
name VARCHAR(255),<br>
age INT<br>
);<br>
</span>

Заполняем таблицу данными:<br>
<span style="color:green">
INSERT INTO users (name, age) VALUES<br>
('Denis', 30),<br>
('Maria', 20),<br>
('Gena', 8);<br>
</span>

Далее запустил приложение и через [rebian](https://reqbin.com/) проверил работоспособность.

## Lessons Learned
Я научился работать с Docker, скачивать images, создавать image по инструкции Dockerfile, создавать контейнеры, создавать сеть.

## Некоторые команды Docker

<span style="color:green">docker ps -a</span><br> показывает все контейнеры и запущенные и нет

<span style="color:green">docker images</span><br> показывает все локальные докер-образы

<span style="color:green">docker rm <имя/id контейнера></span><br> удалить контейнер

<span style="color:green">docker rmi <имя/id image></span><br> удалить докер-образ

<span style="color:green">docker pull <имя докер-образа></span><br> скачать докер образ

<span style="color:green">docker run -d --rm --name name-container <имя докер-образа>:tag</span><br> создает контейнер с именем name-container из докер-образа <имя докер-образа> и версией tag. --rm - говорит о том что контейнер будет удален

## Создание Docker-образа из java приложения(команды)

Создаем докер файл Dockerfile:</br>
<span style="color:green">
FROM openjdk:21</br>
WORKDIR /app</br>
ARG JAR_FILE=build/libs/javaHW-05-0.0.1-SNAPSHOT.jar</br>
COPY ${JAR_FILE} app.jar</br>
EXPOSE 8080</br>
ENTRYPOINT ["java","-jar","app.jar"]</br>

Создаем jar-ник</br>
<span style="color:green">
./gradlew.bat clean build

создаем образ нашего приложения с именем java-hw05:latest ( . точка означает что Dokerfile находится в текущей директории)
</br><span style="color:green">
docker build -t java-hw05:latest .

Создаем контейнер с нашим приложением по докер образу, указываем нашу ранее созданную сеть для приложения и базы данных mydocknet.
Указываем порт внешний и внутренний 8081:8080, указываем контейнер с в котором запущена наша база DB_HOST=mysql-DB
Указываем порт базы  DB_PORT=3307
</br><span style="color:green">
docker run --name container-hw-05 --network mydocknet -p 8081:8080 -e DB_HOST=mysql-DB -e DB_PORT=3307 -d java-hw05:latest


## Authors

- [@Dentapinos](https://github.com/Dentapinos)


