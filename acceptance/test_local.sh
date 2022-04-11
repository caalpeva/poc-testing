#!/bin/bash

sleep 10
CALCULATOR_PORT=$(docker-compose port poc-app-calculator 8080 | cut -d: -f2)
./gradlew acceptanceTest -Dcalculator.url=http://poc-app-calculator:${CALCULATOR_PORT}
