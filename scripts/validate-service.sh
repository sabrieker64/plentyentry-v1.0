#!/bin/bash

status_code=$(curl --write-out %{http_code} --silent --output /dev/null ec2-3-144-231-140.us-east-2.compute.amazonaws.com:8080/hello)

if [[ "$status_code" -ne 200 ]] ; then
  echo "Site status changed to $status_code" | mail -s "SITE STATUS CHECKER" "ekersabri1050@hotmail.com" -r "STATUS_CHECKER"
else
  exit 0
fi