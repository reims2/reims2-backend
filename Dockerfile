FROM eclipse-temurin:21.0.4_7-jdk-jammy@sha256:5121eee404aeb11a6072b0de3fe431001be2921168029f8f789d559e34e7d9a0 AS build

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
FROM eclipse-temurin:21.0.4_7-jre-alpine@sha256:b16f1192681ea43bd6f5c435a1bf30e59fcbee560ee2c497f42118003f42d804
RUN apk add dumb-init

ENV HOST 0.0.0.0
ENV PORT 5000
EXPOSE $PORT

WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/dependencies/ ./
COPY --from=build /usr/src/app/target/spring-boot-loader/ ./
COPY --from=build /usr/src/app/target/snapshot-dependencies/ ./
COPY --from=build /usr/src/app/target/application/ ./

HEALTHCHECK --interval=5s --timeout=10s --retries=8 --start-period=20s CMD wget -nv -t1 --spider http://localhost:$PORT/api || exit 1   

ENTRYPOINT ["/usr/bin/dumb-init", "--"]
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher", "--spring.profiles.active=prod" ]
