#!/bin/bash
java -jar /target/plentyentry-application.jar > /dev/null 2>&1 &
cd ../plentyentry-frontend
ng serve --port 4200