FROM maven
WORKDIR /app
# COPY pom.xml .
# RUN mvn dependency:go-offline
COPY . .
EXPOSE 8080
ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true"
CMD ["mvn", "thorntail:run"]