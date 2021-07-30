FROM openjdk:11-slim as builder

ARG JAR_FILE

LABEL maintainer="Aaron MAJAMBO <aaronmajb@gmail.com>"

COPY ${JAR_FILE}  app.jar

RUN mkdir -p /target/dependency && (cd /target/dependency; jar -xf /app.jar)


FROM openjdk:11-slim

VOLUME /tmp

ARG DEPENDENCY=/target/dependency

COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF  /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app/classes

ENTRYPOINT [ "java","-cp","app:app/lib/*","com.banking.configserver.ConfigServerApplication" ]
