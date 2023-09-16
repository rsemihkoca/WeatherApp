FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src
RUN ./mvnw dependency:resolve
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR weather
COPY --from=build target/*.jar weather.jar
ENTRYPOINT ["java", "-jar", "weather.jar"]