FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "docker-entrypoint.sh"]
#ENTRYPOINT ["java", "-jar", "${JAR_FILE}"]
EXPOSE 8080