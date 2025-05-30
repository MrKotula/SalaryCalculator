FROM openjdk:17

WORKDIR /app

COPY build/libs/SalaryCalculator-0.0.1-SNAPSHOT.jar /app

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "SalaryCalculator-0.0.1-SNAPSHOT.jar"]