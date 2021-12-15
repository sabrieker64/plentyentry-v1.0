#!/bin/bash
java -jar /target/plentyentry-application.jar > /dev/null 2>&1 &
ls
ng serve --port 4200