#!/bin/bash
cd /tmp/|| exit
java -jar target/plentyentry-application.jar
echo "The ApplicationStart deployment lifecycle event successfully completed." > application-start.txt
