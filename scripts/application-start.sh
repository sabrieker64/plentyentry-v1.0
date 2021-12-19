#!/bin/bash
java -jar backend/jar/target/plentyentry-application.jar > /dev/null 2>&1 &
echo Started Spring Boot Backend Application
npm start --prefix plentyentry-frontend/
echo Started Frontend at Port 4200