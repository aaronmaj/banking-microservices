# stage 1
# Base image containing the JDK Runtime
FROM openjdk:11-slim as builder

# Application jar file
ARG JAR_FILE
LABEL maintainer="Aaron MAJAMBO <aaronmajb@gmail.com>"

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar
#unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#stage 2
#Same Java runtime
FROM openjdk:11-slim

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.banking.account.AccountServiceApplication"]