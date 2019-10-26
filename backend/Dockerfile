FROM maven:3.6.2-jdk-8

WORKDIR /usr/src/kombinacija

# copy source
COPY pom.xml .
COPY src ./src

# build executable jar
RUN mvn clean package