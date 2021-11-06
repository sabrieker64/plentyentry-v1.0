#!/bin/bash
java -jar /tmp/plentyentry-application.jar
ng serve
echo "The ApplicationStart deployment lifecycle event successfully completed." > application-start.txt

