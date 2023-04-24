FROM openjdk:17-oracle

COPY ./build/libs/user-service-0.0.*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
