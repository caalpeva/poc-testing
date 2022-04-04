#!/bin/bash
test $(curl localhost:8082/sum?a=1\&b=2) -eq 3
