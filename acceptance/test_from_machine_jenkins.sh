#!/bin/bash

#CALCULATOR_IP=$(docker inspect -f "{{ .NetworkSettings.IPAddress }}" calculator)
sleep 10
CALCULATOR_PORT=$(docker-compose port poc-webapp-calculator 8080 | cut -d: -f2)
./gradlew acceptanceTest -Dcalculator.url=http://poc-webapp-calculator:${CALCULATOR_PORT}
