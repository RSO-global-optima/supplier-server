# Global Optima - Supplier microservice
### Goals
 - CRUD operations on the Supplier entity
 - Make requests for new orders
 - Create reviews for food preparation and delivery services

### Requirements
 - Java openjdk-19.0.1
 - Maven 3.8.6
 - Running postgres server on localhost  
`docker run -d --name pg-supplier -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=suppliers -p 5434:5432 postgres:15.1`

Check the correct versions with `mvn --version`

### How to run
 - build the project `mvn clean package`  
 - run locally `java -jar target/supplier-server-1.0-SNAPSHOT.jar`  
 - Service can be accessed via [http://localhost:8082/v1/suppliers](http://localhost:8082/v1/suppliers)
 - openapi documentation can be viewed via [http://localhost:8082/openapi/ui](http://localhost:8082/openapi/ui)

