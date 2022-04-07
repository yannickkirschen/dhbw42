# DHBW42 - Backend

This is the backend for DHBW42. It is implemented as a Spring Boot Service.

## Run the server

You can access the server under http://localhost:8000/v1. The Swagger API is available
under http://localhost:8000/v1/swagger.

### Via Java

```bash
mvn package
java -jar target/app.jar
```

### Via Docker

```
docker build -t dhbw42-server .
docker run --rm -ti -p 8000:8000 dhbw42-server dhbw42-server
```
