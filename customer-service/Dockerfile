FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR extracted
LABEL maintainer ="Aaron MAJAMBO <aaronmajb@gmail.com>"
ARG JAR_FILE
ADD ${JAR_FILE}  app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR application
EXPOSE 8080
COPY --from=builder extracted/dependencies ./
COPY --from=builder extracted/spring-boot-loader ./
COPY --from=builder extracted/applicatoion ./
COPY --FROM=builder extracted/snapshot-dependencies ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLaucher"]
