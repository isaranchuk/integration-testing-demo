# integration-testing-demo

Demo project to show component and integration testing approach for microservices architecture.

## Overview
Project represents activities service that exposes two REST APIs:
* `/v1/users/{username}/activities/any` to get any activity for you're bored
* `/v1/users/{username}/activities/history` to list all your previous activities

## Technologies 
The following technologies are used:  
* Docker and docker-compose
* Cucumber
* Wiremock
* RestAssured

### Running tests
There're two ways of running tests:
* with script
* with IDE

But first you need to build custom Docker images: `activities-service` and `activities-service`
```bash
$ ./bin/build-image.sh
```

### Running tests with script
This way is useful when you want to run tests on Jenkins or in another automated way.  
You just need to launch test script that will start `docker-compose` environment and run tests:  
```bash
$ ./bin/test-image.sh
```

### Running tests with IDE
This way is useful when you want to debug tests and see more logs.  
First you have to start up `docker-compose` environment with `dev` extension:  
```bash
$ docker-compose -f integration/docker-compose.yml -f integration/docker-compose.dev.yml up
```
Then you just need to run `RunCukesTest.java` in your favorite IDE.   