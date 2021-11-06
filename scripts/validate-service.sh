#!/bin/bash
chmod u+x/tmp/plentyentry-application.jar
service plentyentry-application start
echo "Validating Service, Application Start Success" > validate-service.txt