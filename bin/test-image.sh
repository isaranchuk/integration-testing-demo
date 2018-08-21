#!/usr/bin/env bash
set -xe

IMAGE_NAME=activities-service
INTEGRATION_IMAGE_NAME=$IMAGE_NAME-integration
RUN_TESTS_COMMAND="java -jar app.jar --plugin \"pretty\" --glue \"classpath:com.isaranchuk\" /test/features/"

(
echo "Running integration tests"
docker-compose -p $INTEGRATION_IMAGE_NAME -f integration/docker-compose.yml \
    run --rm \
    $INTEGRATION_IMAGE_NAME \
    sh -c "./waitfor.sh $IMAGE_NAME:8080 && $RUN_TESTS_COMMAND"

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
