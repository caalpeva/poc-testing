#!/bin/bash

#CALCULATOR_IP=$(docker inspect -f "{{ .NetworkSettings.IPAddress }}" calculator)
sleep 10
CALCULATOR_PORT=$(docker-compose port calculator 8080 | cut -d: -f2)
#test $(curl calculator:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
./gradlew acceptanceTest -Dcalculator.url=http://calculator:${CALCULATOR_PORT}
