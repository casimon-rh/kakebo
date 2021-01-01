FROM maven:3.5-jdk-8 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /build/src/
RUN mvn package

FROM tomcat:10-jdk8-openjdk as run
COPY --from=build /build/target /usr/local/tomcat/webapps/.
EXPOSE 8080