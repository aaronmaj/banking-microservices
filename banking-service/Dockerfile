FROM adoptopenjdk:11-jre-hotspot AS BUILDER
WORKDIR extracted
LABEL maintainer = "Aaron MAJAMBO <aaronmajb@gmail.com>"
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR application

EXPOSE 8080
COPY --from=BUILDER extracted/dependencies  ./
COPY --from=BUILDER extracted/spring-boot-loader ./
COPY --from=BUILDER extracted/application ./
COPY --from=BUILDER extracted/snapshot-dependencies        ./

ENTRYPOINT["java", "org.springframework.boot.loader.JarLauncher"]