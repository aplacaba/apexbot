FROM openjdk:8-alpine

# Make a directory
RUN mkdir -p /app
WORKDIR /app

# Copy only the target jar over
COPY target/apexbot-standalone.jar .

# Run the JAR
CMD java -jar apexbot-standalone.jar
