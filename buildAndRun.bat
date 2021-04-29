@echo off
call mvn clean package
call docker build -t com.mycompany/MyTaskList .
call docker rm -f MyTaskList
call docker run -d -p 9080:9080 -p 9443:9443 --name MyTaskList com.mycompany/MyTaskList