# Use the lighter version of Eclipse Temurin JRE for Java 21 as the base image
FROM eclipse-temurin:21.0.4_7-jre-alpine as builder

# Set the working directory
WORKDIR /extracted

# Add the application's jar file to the image
ADD ./build/libs/*.jar app.jar

# Extract the layers from the jar file
RUN java -Djarmode=layertools -jar app.jar extract

# Use a lightweight version of the Eclipse Temurin JRE for Java 21 for the final image
FROM eclipse-temurin:21.0.4_7-jre-alpine

# Set the working directory for the application
WORKDIR /application

# Copy the extracted layers from the builder stage
COPY --from=builder /extracted/dependencies/ ./
COPY --from=builder /extracted/spring-boot-loader/ ./
COPY --from=builder /extracted/snapshot-dependencies/ ./
COPY --from=builder /extracted/application/ ./

# Expose the port for the application
EXPOSE 8080

# Set the entry point to launch the Spring Boot application
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
