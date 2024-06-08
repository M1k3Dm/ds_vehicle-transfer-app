FROM openjdk:21-rc-oracle
MAINTAINER argirisdak
WORKDIR /vehicle-transfer-app
COPY target/fullstack-vehicle-transfer-0.0.1-SNAPSHOT.jar ./application.jar
EXPOSE 8080
ENTRYPOINT ["java","-XX:+TieredCompilation","-XX:TieredStopAtLevel=1","-jar","application.jar"]