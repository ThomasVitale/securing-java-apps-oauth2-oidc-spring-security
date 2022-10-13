# Securing Java applications with OAuth2 and OIDC using Spring Security

Source code and examples from my presentation.

## Prerequisites

To run all the examples, you need to install the following tools:

* [Java 17](https://adoptium.net)
* [Docker](https://www.docker.com)

## Usage

The sample applications rely on Redis, Keycloak, and an Angular SPA. You can run them as containers with the following command:

```shell
$ docker-compose up -d
```

Both Spring Boot applications can be run locally with this command:

```shell
$ ./gradlew bootRun
```
