#!/bin/bash
chmod u+x/tmp/plentyentry-application.jar
service plentyentry-application start
echo "The ApplicationStart deployment lifecycle event successfully completed." > application-start.txt