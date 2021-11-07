#!/bin/bash
chmod u+x../target/plentyentry-application.jar
java -jar ../target/plentyentry-application.jar
echo "The ApplicationStart deployment lifecycle event successfully completed." > application-start.txt