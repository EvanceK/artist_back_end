# 使用 Maven 構建
FROM maven:3.8.1-openjdk-17 AS artistjava2024
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 使用 JDK 執行 Spring Boot 應用
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=artistjava2024 /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]