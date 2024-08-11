FROM openjdk:21

COPY build/libs/account-0.0.1-SNAPSHOT.jar /app/account.jar

WORKDIR /app

EXPOSE 8085

CMD ["java", "-jar", "account.jar"]