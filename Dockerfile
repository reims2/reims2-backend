FROM eclipse-temurin:21.0.1_12-jdk-jammy@sha256:b6076d31c45f26919a942fc8f8a0a4f14411001d4344352796f5ca042a4d0533 AS build
RUN apt-get update && apt-get install -y --no-install-recommends dumb-init

ENV PORT 5000
WORKDIR /usr/src/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline
COPY ./src ./src
RUN --mount=type=cache,target=/root/.m2 ./mvnw clean install -DskipTests

# PROD IMAGE
FROM eclipse-temurin:21.0.1_12-jre-jammy@sha256:8e6ea561ce4b5e02960b16f2578b3db4758f1d47a479de2194cd10f8e25df2d1
RUN apt-get update && apt-get upgrade -y && apt install curl -y && rm -rf /var/lib/apt/lists/*

ENV HOST 0.0.0.0
ENV PORT 5000

WORKDIR /usr/src/app
COPY --from=build /usr/bin/dumb-init /usr/bin/dumb-init
COPY --from=build /usr/src/app/target/*.jar ./app.jar

EXPOSE 5000

HEALTHCHECK --interval=5s --timeout=10s --retries=5 --start-period=20s CMD curl --fail http://localhost:$PORT/api || exit 1   


ENTRYPOINT ["/usr/bin/dumb-init", "--"]
CMD ["java",  "-jar", "/usr/src/app/app.jar", "--spring.profiles.active=mysql" ]
