#!/bin/bash
ls
java -jar /target/plentyentry-application.jar > /dev/null 2>&1 &
echo Started Spring Boot Backend Application
cd ../../plentyentry-frontend || exit
ng serve --port 4200
echo Started Frontend at Port 4200