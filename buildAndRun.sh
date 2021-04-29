#!/bin/sh
mvn clean package && docker build -t com.mycompany/MyTaskList .
docker rm -f MyTaskList || true && docker run -d -p 9080:9080 -p 9443:9443 --name MyTaskList com.mycompany/MyTaskList