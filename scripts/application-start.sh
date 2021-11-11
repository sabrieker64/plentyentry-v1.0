#!/bin/bash
nohup java -jar ../target/plentyentry-application.jar > application-start.txt
echo $! > ../target/pid.file