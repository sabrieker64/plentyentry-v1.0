#!/bin/bash

status_code=$(curl --write-out %{http_code} --silent --output /dev/null ec2-3-144-231-140.us-east-2.compute.amazonaws.com:8080/api/backend/event/list)

if [[ "$status_code" -ne 200 ]] ; then
  echo "Site status changed to $status_code"
else
  exit 0
fi