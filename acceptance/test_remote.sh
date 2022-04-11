#!/bin/bash

sleep 10
CALCULATOR_PORT=9999
test $(curl poc-app-calculator:${CALCULATOR_PORT}/sum?a=1\&b=2) -eq 3
