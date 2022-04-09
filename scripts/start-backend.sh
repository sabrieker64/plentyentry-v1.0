#!/bin/bash
sudo java -jar -Dspring.profiles.active=qa plentyentry-application.jar > /dev/null 2> /dev/null < /dev/null &