FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
COPY --from=build /target/*.jar petflix.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/petflix.jar"]
