#!/bin/bash

sleep 10
CALCULATOR_PORT=8080
CALCULATOR_IP=$(docker inspect -f "{{ .NetworkSettings.IPAddress }}" calculator)
./gradlew acceptanceTest -Dcalculator.url=http://${CALCULATOR_IP}:${CALCULATOR_PORT}
#test $(curl ${CALCULATOR_IP}:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
