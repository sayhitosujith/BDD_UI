#!/bin/bash

# Execute wait script based on environment configs
./docker/utilities/wait

echo "Using Params: --no-dry-run --tags $1"

# Kinda works
mvn test -Dcucumber.options="--no-dry-run --tags $1"
