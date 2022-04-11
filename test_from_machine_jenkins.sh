#!/bin/bash

sleep 10
CALCULATOR_PORT=$(docker-compose port poc-webapp-calculator 8080 | cut -d: -f2)
echo "Puerto ${CALCULATOR_PORT}"
./gradlew acceptanceTest -Dcalculator.url=http://localhost:${CALCULATOR_PORT}
