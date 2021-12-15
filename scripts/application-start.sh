#!/bin/bash
ls
echo Starting Spring Boot Backend Application
java -jar /target/plentyentry-application.jar > /dev/null 2>&1 &
echo Starting Frontend at Port 4200
cd plentyentry-frontend
ng serve --port 4200