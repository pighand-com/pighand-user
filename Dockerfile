FROM java:8
VOLUME /tmp
ARG JAR_FILE=/target/erlangshen.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]