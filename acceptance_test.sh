#!/bin/bash

CALCULATOR_IP=$(docker inspect -f "{{ .NetworkSettings.Networks.IPAddress }}" calculator)
echo "Extract ip from container: ${CALCULATOR_IP}"
CALCULATOR_PORT=$(docker-compose port calculator 8080 | cut -d: -f2)
echo "Extract ip from container: ${CALCULATOR_PORT}"
test $(curl ${CALCULATOR_IP}:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
