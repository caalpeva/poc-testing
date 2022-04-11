#!/bin/bash

CALCULATOR_PORT=8080
./gradlew acceptanceTest -Dcalculator.url=http://localhost:${CALCULATOR_PORT}
