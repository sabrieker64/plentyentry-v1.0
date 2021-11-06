#!/bin/bash
chmod u+x/tmp/plentyentry-application.jar
service plentyentry-application stop
echo "The ApplicationStart deployment lifecycle event successfully completed." > application-stop.txt