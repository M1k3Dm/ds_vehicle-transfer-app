FROM openjdk:21-rc-oracle as builder
WORKDIR /ds_vehicle-transfer-app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw  package -Dmaven.test.skip

FROM openjdk:19-jdk-alpine3.16
##RUN apk update && apk add curl
WORKDIR /ds_vehicle-transfer-app
# Set non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

EXPOSE 9090
COPY --from=builder /ds_vehicle-transfer-app/target/*.jar /ds_vehicle-transfer-app/*.jar
ENTRYPOINT ["java", "-jar", "/ds_vehicle-transfer-app/*.jar" ]