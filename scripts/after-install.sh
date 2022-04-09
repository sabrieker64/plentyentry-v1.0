#!/usr/bin/env bash
chmod +x /home/ec2-user/server/* jar
chmod +x /home/ec2-user/server/start_backend.sh
echo "The AfterInstall deployment lifecycle event successfully completed." > after-install.txt