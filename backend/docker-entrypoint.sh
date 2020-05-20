#!/bin/bash

mvn clean package
java -jar ${JAR_FILE}.jar