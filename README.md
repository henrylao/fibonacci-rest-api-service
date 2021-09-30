# Fibonacci & Prime REST API

Overview
--



Startup
--
To start the REST application run the following in the project root

```bash
mvn spring-boot:run
```

This Restful API demo was done with Spring Boot

Calculates the Fibonacci number of a certain position

The results are returned in JSON implementing the IETF devised RFC 7807 error-handling
schema (https://tools.ietf.org/html/rfc7807)

To obtain results with large numbers, BigInteger has been used for calculations

It has error control in parameters and their corresponding tests

The info and error messages have been written in English to give uniformity to the output of messages, since some
messages are the result of exceptions

Timeout control, if the calculation operation takes more than 60 seconds, it returns an error.

It is ready to work on port `8080`

The URI path is `/fibonacci/`

The `position` para``meter indicates the position to be calculated, it must be 0 or positive

URL `protocol://host:8080/fibonacci/{position}`

CLI Testing
---
Using `curl` perform the following

* **DELETE**

```bash
# Delete all
curl -X DELETE http://localhost:8080/api//v1//fibonacci/delete/all
# Delete specific
curl -X DELETE http://localhost:8080/api//v1//fibonacci/delete/?id=0

```

* **GET**

```bash
# View all
curl -v http://localhost:8080/api/v1/fibonacci/get/all

# View specific
curl -v http://localhost:8080/api/v1/fibonacci/get/?id=5

```

8 **POST**

```bash
# Create Fibonacci elements from index 0 to 10 of series
curl -d "id=10" http://localhost:8080//api/v1/fibonacci/add/
```

### TODO

- [ ] For a given number input, check if this is prime number OR not.
- [ ] Generate a fibonacci series for 15 numbers
- [ ] Create a REST API –
    - [x] POST - Store the data in a file
    - [x] GET – Read the data from a file
- [ ] Create a REST API
    - [ ] PUT – update the data in a file
    - [x] DELETE – delete the data from a file.
  
Dependencies
--
* H2 Database
* WebDev Tools
* Spring Boot Actuator

### Reference Documentation

For further reference, please consider the following sections:

**Spring Boot & REST**

* [Full Walkthrough](https://www.jetbrains.com/idea/guide/tutorials/getting-started-spring-data-jpa/creating-repository-class/)
* [Spring Boot CRUD RESTful Service](https://www.springboottutorial.com/spring-boot-crud-rest-service-with-jpa-hibernate)
* [Internet Engineering Task Force RFC 7807](https://tools.ietf.org/html/rfc7807)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/#build-image)
* [Jersey](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-jersey)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#using-boot-devtools)

**HTTP Protocol**

* [About RFC 7807](https://datatracker.ietf.org/doc/html/rfc7807)

