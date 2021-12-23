#!/bin/bash
chmod -R 755 /tmpDir/
java -jar ../target/plentyentry-application.jar > backend-start-log.txt
echo Started Spring Boot Backend Application at port 8080

