#!/bin/bash
java -jar -Dspring.profiles.active=qa /home/ec2-user/server/plentyentry-application.jar > backend-log.txt