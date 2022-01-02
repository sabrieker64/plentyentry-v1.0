#!/bin/bash
cd /home/ec2-user
npm start > /dev/null 2> /dev/null < /dev/null &
echo Started Frontend at Port 4200