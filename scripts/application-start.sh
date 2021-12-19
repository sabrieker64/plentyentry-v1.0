#!/bin/bash
java -jar target/plentyentry-application.jar
echo Started Spring Boot Backend Application at port 8080
npm start --prefix plentyentry-frontend/
echo Started Frontend at Port 4200
