FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/api-gateway.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8084
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]