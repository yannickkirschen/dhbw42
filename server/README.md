# DHBW42 - Backend

This is the backend for DHBW42. It is implemented as a Spring Boot Service.

## Prepare the database

This application uses PostgreSQL. If you don't have a running instance, you can
start a development database by using a container (e.g. via Podman or Docker).

The default schema this application uses, is `DHBW42`. If you want to use a
different schema, you can edit the JDBC URL in `application.yml`. When you
remove the `currentSchema`, `public` is used as a default.

To create the schema `DHBW42`, connect to your database and execute the
script `.scripts/schema.sql`.

The default user/password is `postgres/1234`. You can change this
in `application.yml` as well, or be overriding the properties via environment
variables.

### Start a development database

`podman run --rm -ti --name postgres -e POSTGRES_PASSWORD=1234 -p 5432:5432 -d postgres`

## Run the server

You can access the server under http://localhost:8000/v1. The Swagger API is
available
under http://localhost:8000/v1/swagger.

### Via Java

```bash
mvn package
java -jar target/app.jar
```

### Via Docker

```
podman build -t dhbw42-server .
podman run --rm -ti -p 8000:8000 dhbw42-server dhbw42-server
```
