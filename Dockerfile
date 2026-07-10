FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y maven
WORKDIR /tests
COPY pom.xml .   
# Remove -q so you can see if dependencies download correctly
RUN mvn dependency:go-offline dependency:resolve-plugins
COPY src ./src
# Removed -q and added property to force immediate log flushes
CMD ["mvn", "test", "-Dsurefire.skipAfterFailureCount=0", "-Dorg.slf4j.simpleLogger.defaultLogLevel=info"]

