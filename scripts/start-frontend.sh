#!/bin/bash
chmod -R 755 /tmp/
npm start --prefix ../plentyentry-frontend/  > frontend-start-log.txt
echo Started Frontend at Port 4200