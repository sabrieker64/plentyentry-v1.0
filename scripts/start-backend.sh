#!/bin/bash
mvn clean package
java -jar ../target/plentyentry-application.jar > backend-start-log.txt
echo Started Spring Boot Backend Application at port 8080
