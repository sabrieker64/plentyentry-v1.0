#!/bin/bash
java -jar /target/plentyentry-application.jar > application-start.txt
cd /plentyentry-frontend/dist/plentyentry-frontend
ng serve --port 4200