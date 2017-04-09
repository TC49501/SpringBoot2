# spring-boot-rest-example
Simple example of a REST Service in Spring Boot using JPA/Rest Repositories/h2database/Actuator

To build this project
```bash
mvn clean package
```

To run this project
```bash
mvn spring-boot:run
```

Try
```
http://localhost:8080/employees/
```
http://localhost:8080/employees/1
```

#Deploy in cloud foundry
cf push rest-app-in-five-mins -p target/demo-0.0.1-SNAPSHOT.jar

#Access public url
https://rest-app-in-five-mins.cfapps.io

Actuator provide different metrics
http://localhost:8080/actuator
/actuator/autoconfig/beans/configprops/dump/env/health/info/metrics/mappings/shutdown/trace/logfile/flyway/liquibase
