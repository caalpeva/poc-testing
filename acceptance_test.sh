#!/bin/bash

CALCULATOR_IP=$(docker inspect -f "{{ .NetworkSettings.IPAddress }}" calculator)
echo "IP address: ${CALCULATOR_IP}"
CALCULATOR_PORT=8082
#CALCULATOR_PORT=$(docker-compose port calculator 8080 | cut -d: -f2)
echo "Port: ${CALCULATOR_PORT}"
test $(curl ${CALCULATOR_IP}:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
