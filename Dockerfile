FROM eclipse-temurin:21.0.3_9-jdk-jammy@sha256:08553a9e5fa00ff728e4b69a390d49b08785675173d8384f38c27da2b49b0fc1 AS build

ENV PORT 5000
WORKDIR /usr/src/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline

COPY ./src ./src
RUN --mount=type=cache,target=/root/.m2 ./mvnw clean install -DskipTests

WORKDIR /usr/src/app/target
RUN java -Djarmode=layertools -jar ./*.jar extract

# PROD IMAGE
FROM eclipse-temurin:21.0.3_9-jre-alpine@sha256:f05c742dd20051b104b939370f7db4d6eb420cc7fd842aeb4e2446837da3bd03
RUN apk add dumb-init

ENV HOST 0.0.0.0
ENV PORT 5000
EXPOSE $PORT

WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/dependencies/ ./
COPY --from=build /usr/src/app/target/spring-boot-loader/ ./
COPY --from=build /usr/src/app/target/snapshot-dependencies/ ./
COPY --from=build /usr/src/app/target/application/ ./

HEALTHCHECK --interval=5s --timeout=10s --retries=5 --start-period=20s CMD wget -nv -t1 --spider http://localhost:$PORT/api || exit 1   

ENTRYPOINT ["/usr/bin/dumb-init", "--"]
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher", "--spring.profiles.active=prod" ]
