#!/usr/bin/env bash
set -xe

echo "Building service Docker image"

mvn clean package

docker build -t activities-service:latest .

echo "Building integration Docker image"

pushd integration

mvn clean package -DskipTests

docker build -t activities-service-integration:latest .

popd
