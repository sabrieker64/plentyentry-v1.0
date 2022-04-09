#!/usr/bin/env bash
sudo chmod +x /home/ec2-user/server/plentyentry-application.jar
sudo chmod +x /home/ec2-user/server/start-backend.sh
echo "The AfterInstall deployment lifecycle event successfully completed." > after-install.txt