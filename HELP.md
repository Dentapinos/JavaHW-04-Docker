
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

## Authors

- [@Dentapinos](https://github.com/Dentapinos)


