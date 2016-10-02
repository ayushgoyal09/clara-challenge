GIF Search Application
======================================================
Application is built using Spring Boot and Java 1.8
Spring Boot comes with embedded tomcat so tomcat is not required

Instruction to run Application
=====================================================
mvn spring-boot:run

API Access
=====================================================
http://{server}:8080/api/v1/giphy/search/{query}
For example
http://192.241.192.190:8080/api/v1/giphy/search/cat
The results returned is also configurable
http://192.241.192.190:8080/api/v1/giphy/search/cat?limit=10
