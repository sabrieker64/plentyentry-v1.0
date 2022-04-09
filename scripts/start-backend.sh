#!/bin/bash
sudo java -jar -Dspring.profiles.active=qa /home/ec2-user/server/plentyentry-application.jar > /dev/null 2> /dev/null < /dev/null &