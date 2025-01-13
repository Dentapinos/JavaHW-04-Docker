FROM openjdk:21
WORKDIR /app
ARG JAR_FILE=build/libs/javaHW-05-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
