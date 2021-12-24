#!/bin/bash
chmod -R a+X /tmpDir
java -jar ../target/plentyentry-application.jar > backend-start-log.txt
echo Started Spring Boot Backend Application at port 8080

