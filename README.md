# ktor-demo

![Java CI with Maven](https://github.com/larmic/ktor-demo/workflows/Java%20CI%20with%20Maven/badge.svg)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Simple ktor jackson example.

## Used technologies

* Ktor 1.5.0
* Kotlin 1.4.21-2
* JUnit 5.7.0
* assertk 0.23
* Docker
* GitHub Actions (CI)

## Requirements

* Java 11
* Maven >= 3.2.1 (Kotlin comes as maven dependency)

## Clone repository and build project with tests

```ssh
$ git clone https://github.com/larmic/ktor-demo
$ mvn clean verify
```

### Fat-Jar

```ssh
# build application
$ mvn clean package -DskipTests

# start application
$ java -jar target/ktor-*-with-dependencies.jar 
```

### Docker in JVM mode

```ssh
# build application
$ mvn clean package -DskipTests

# build docker image
$ docker build -t larmic/ktor-demo-jvm -f src/main/docker/Dockerfile.jvm .

# start application
$ docker run -i --rm -p 8080:8080 larmic/ktor-demo-jvm
```

## Local testing

```ssh
# HTTP request examples
# Get root
$ curl --request GET http://localhost:8080/

# Get all tasks
$ curl -i -H "Content-Type: application/json" --request GET http://localhost:8080/tasks

# Get one task   
$ curl -i -H "Content-Type: application/json" --request GET http://localhost:8080/tasks/1      
```