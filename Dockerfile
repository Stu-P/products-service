FROM openjdk:14-jdk-alpine

# Copy the artifact into the container
COPY target/products-*.jar /srv/service.jar

# Run the artifact and expose the default port
WORKDIR /srv

ENTRYPOINT [ "java", \
    "-XX:+UnlockExperimentalVMOptions", \
    "-XX:+ExitOnOutOfMemoryError", \
    "-XX:MaxRAMPercentage=75.0", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", "service.jar", \
    "--spring.profiles.active=prod" ]

EXPOSE 8080
