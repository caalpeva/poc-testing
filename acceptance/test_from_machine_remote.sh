#!/bin/bash

sleep 10
CALCULATOR_PORT=8080
test $(curl poc_webapp_calculator:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
