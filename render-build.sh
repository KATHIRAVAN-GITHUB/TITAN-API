#!/bin/bash

# Install OpenJDK 17
curl -fsSL https://download.java.net/openjdk/jdk17/ri/openjdk-17+35_linux-x64_bin.tar.gz | tar xz -C /tmp
export JAVA_HOME=/tmp/jdk-17
export PATH=$JAVA_HOME/bin:$PATH

# Verify Java Installation
java -version

# Install Maven manually (since apt-get does not work)
curl -fsSL https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz | tar xz -C /tmp
export MAVEN_HOME=/tmp/apache-maven-3.9.6
export PATH=$MAVEN_HOME/bin:$PATH

# Verify Maven Installation
mvn -v

# Build the Spring Boot application
mvn clean package -DskipTests
