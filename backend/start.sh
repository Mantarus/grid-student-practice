#!/bin/bash

mvn clean package -D active.profiles=test,orm
docker-compose build --no-cache app
docker-compose up