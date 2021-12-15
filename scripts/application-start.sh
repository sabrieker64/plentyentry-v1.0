#!/bin/bash
java -jar /target/plentyentry-application.jar > /dev/null 2>&1 &
echo First
ls
cd ../
echo Second
ls
cd ../
echo Third
ls
cd plentyentry-frontend || exit
ng serve --port 4200