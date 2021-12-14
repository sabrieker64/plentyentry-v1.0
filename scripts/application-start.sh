#!/bin/bash
ls
cd ../
java -jar /target/plentyentry-application.jar > /dev/null 2>&1 &
cd ../plentyentry-frontend || exit
ng serve --port 4200