#!/bin/bash
export FOLDER=/tmp/
if [ -d $FOLDER ]
then
 rm -rf $FOLDER
fi
mkdir -p $FOLDER