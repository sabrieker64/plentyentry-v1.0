#!/bin/bash
cd /home/ec2-user/frontend || exit
npm i @angular/cli
npm install
ng serve --port 4200 > /dev/null 2> /dev/null < /dev/null &
echo Started Frontend at Port 4200