#!/bin/bash
cd /home/ec2-user/target || exit
java -jar -Dspring.profiles.active=qa plentyentry-application.jar > /dev/null 2> /dev/null < /dev/null &