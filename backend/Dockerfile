FROM maven:3.6.2-jdk-8 AS builder

WORKDIR /usr/src/kombinacija

# copy source files
COPY pom.xml .
COPY src ./src

# copy production properties
COPY src/main/resources/application-prod.properties ./config/application.properties

# build executable jar
RUN mvn -f pom.xml clean package


# -----------------------------------------------------------


FROM openjdk:8 AS runner

WORKDIR /usr/src/kombinacija

# copy built jar and props
COPY --from=builder /usr/src/kombinacija/target/manje-smece-vise-srece-*.jar manje-smece-vise-srece.jar
COPY --from=builder /usr/src/kombinacija/config ./config

# run application
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "manje-smece-vise-srece.jar"]