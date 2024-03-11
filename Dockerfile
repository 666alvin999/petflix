FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM oraclelinux-8
COPY --from=build /target/petflix-0.0.1-SNAPSHOT.jar petflix.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","petflix.jar"