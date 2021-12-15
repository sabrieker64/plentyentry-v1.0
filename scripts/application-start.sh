#!/bin/bash
java -jar /target/plentyentry-application.jar > /dev/null 2>&1 &
ls
cd ../
ls
cd ../
ls
cd plentyentry-frontend || exit
ng serve --port 4200