#!/bin/bash

pushd service-a-api
./gradlew bootjar
./docker-build.sh
popd
pushd service-b-lol
./gradlew bootjar
./docker-build.sh
popd
pushd service-c-db
./gradlew bootjar
./docker-build.sh
popd
