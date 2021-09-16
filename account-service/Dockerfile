# syntax=docker/dockerfile:1
FROM openjdk:11-slim as builder

ARG JAR_FILE
LABEL maintainer="Aaron MAJAMBO <aaronmajb@gmail.com>"
COPY ${JAR_FILE} app.jar
#unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)


FROM openjdk:11-slim

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
#To use from Spring Boot v2.3.0 or higher.
ARG DEPENDENCY=/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.banking.account.AccountServiceApplication"]