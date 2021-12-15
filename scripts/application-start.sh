#!/bin/bash
ls
echo Starting Spring Boot Backend Application
java -jar /target/plentyentry-application.jar > /dev/null 2>&1 &
echo Starting Frontend at Port 4200
cd ../deployment-root/bea34b85-d2c6-4bec-bd32-31dbadd0a20d/d-IX9W57EKD/deployment-archive/plentyentry-frontend || exit
ng serve --port 4200