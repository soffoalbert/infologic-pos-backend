FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/business.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8087
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]