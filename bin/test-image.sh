#!/usr/bin/env bash

source ./bin/image-version.sh

$(${AWS_CMD} ecr get-login --no-include-email)

(
echo "Running integration tests"
docker-compose -p $INTEGRATION_IMAGE_NAME -f integration/docker-compose.yml \
    run --rm \
    $INTEGRATION_IMAGE_NAME \
    sh -c "./waitfor.sh $IMAGE_NAME 8080"

TEST_RESULT=$?
if [[ "$TEST_RESULT" -ne 0 ]]; then
    echo -e "Incorrect test result ${TEST_RESULT}"
    exit $TEST_RESULT
fi

echo -e "Passed"
docker-compose -p $INTEGRATION_IMAGE_NAME -f integration/docker-compose.yml down -v
) ||  (
docker-compose -p $INTEGRATION_IMAGE_NAME -f integration/docker-compose.yml down -v
echo -e "Failed"
exit 1
)
