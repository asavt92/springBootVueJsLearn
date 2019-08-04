FROM openjdk:8-jre-alpine

WORKDIR /app

COPY ./build/libs/*.jar /app/server.jar

EXPOSE 8080

CMD "chmod +x /app/server.jar"

CMD ["java", "-jar", "/app/server.jar"]