# build maven
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Dmaven.compiler.parameters=true

# runtime / api
FROM eclipse-temurin:21-jre

ENV APP_USER=appuser \
    APP_HOME=/opt/global-solution-java \
    APP_PORT=8080

RUN useradd -m -d ${APP_HOME} ${APP_USER} && \
    chown ${APP_USER}:${APP_USER} ${APP_HOME}

WORKDIR ${APP_HOME}
RUN chown ${APP_USER}:${APP_USER} ${APP_HOME}

COPY --from=build --chown=${APP_USER}:${APP_USER} /app/target/Global-Solution-Java-0.0.1-SNAPSHOT.jar ${APP_HOME}/app.jar

USER ${APP_USER}

EXPOSE ${APP_PORT}

CMD ["java", "-jar", "app.jar"]
