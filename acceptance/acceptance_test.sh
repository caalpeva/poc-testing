#!/bin/bash

#CALCULATOR_IP=$(docker inspect -f "{{ .NetworkSettings.IPAddress }}" calculator)
#CALCULATOR_PORT=$(docker-compose port calculator 8080 | cut -d: -f2)
sleep 10
test $(curl calculator:8080/sum?a=1\&b=2) -eq 3
#./gradlew acceptanceTest -Dcalculator.url=http://calculator:8080
