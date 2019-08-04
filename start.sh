#!/bin/bash

./gradlew bootJar && \
docker build --tag=my-app .
