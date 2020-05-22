#!/bin/bash

mvn clean package -D active.profile=orm
docker-compose build --no-cache app
docker-compose up