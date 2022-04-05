#!/bin/bash
#CALCULATOR_PORT=$(docker-compose port calculator 8080 | cut -d: -f2)
#echo "El numero de puerto es ${CALCULATOR_PORT}"
#test $(curl localhost:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
test $(curl localhost:8082/sum?a=1\&b=2) -eq 3
