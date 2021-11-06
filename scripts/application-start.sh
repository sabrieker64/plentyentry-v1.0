#!/bin/bash
java -jar /tmp/plentyentry-application.jar
ng serve --port 8081
echo "The ApplicationStart deployment lifecycle event successfully completed." > application-start.txt

