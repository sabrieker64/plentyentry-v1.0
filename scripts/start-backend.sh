#!/bin/bash
java -jar ../target/plentyentry-application.jar
cd ../plentyentry-frontend || exit
npm start --prefix ../plentyentry-frontend
echo Started Spring Boot Backend Application at port 8080
