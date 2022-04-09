#!/bin/bash
export FOLDER=/
if [ -d $FOLDER ]
then
 rm -rf $FOLDER
fi
mkdir -p $FOLDER