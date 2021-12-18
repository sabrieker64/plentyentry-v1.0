#!/bin/bash
java -jar backend/jar/target/plentyentry-application.jar > /dev/null 2>&1 &
echo Started Spring Boot Backend Application
cd frontend/dist || exit
ng serve --port 4200
echo Started Frontend at Port 4200