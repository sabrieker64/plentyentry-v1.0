#!/bin/bash
export FOLDER=/home/ec2-user
if [ -d $FOLDER ]
then
 rm -rf $FOLDER
fi
mkdir -p $FOLDER