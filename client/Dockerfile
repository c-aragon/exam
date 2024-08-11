FROM openjdk:21

COPY build/libs/client-0.0.1-SNAPSHOT.jar /app/client.jar

WORKDIR /app

EXPOSE 8086

CMD ["java", "-jar", "client.jar"]