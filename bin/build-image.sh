#!/usr/bin/env bash
set -xe

echo "Building service Docker image"

mvn clean package

docker build --build-arg image_name=activities-service -t activities-service:latest .

echo "Building integration Docker image"

pushd integration

mvn clean package -DskipTests

docker build --build-arg image_name=activities-service-integration -t activities-service-integration:latest .

popd
