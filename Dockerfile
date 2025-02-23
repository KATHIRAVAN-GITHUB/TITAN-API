# Use an official OpenJDK image with Maven pre-installed
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Run the application
CMD ["java", "-jar", "target/*.jar"]
