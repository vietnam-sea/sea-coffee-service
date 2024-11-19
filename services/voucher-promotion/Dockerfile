FROM openjdk:17 AS base
USER app
WORKDIR /application

FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /application-builder
COPY . .
RUN mvn clean package -pl :voucher-promotion -am -DskipTests

FROM base AS deploy
WORKDIR /application
COPY --from=builder /application-builder/services/voucher-promotion/target/*.jar ./app.jar
USER app
ENTRYPOINT ["java", "-jar", "app.jar"]