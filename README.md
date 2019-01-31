# HEB Developer 2 Challenge API

This project was created to satisfy the backend requirements of the challenge issued by HEB for Developer 2s. This project creates a Java API to perform CRUD operations on a database of grocery Items. The specific operations available are: CreateItem, CreateItems, DeleteItem,
FindAllItems, FindItemsByString and UpdateItem.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You will need: Eclipse, Java 8, Tomcat, Mongodb, JUnit, Swagger, Jersey, Log4j and Maven

### Installing

To get the project up and running, clone the repo and open in Eclipse.
Open terminal and navigate to directory and run:

```
npm install --save
npm update --save
```

In eclipse, right click project parent directory and select Run-As -> Run on Server.
A web page should open up on: http://localhost:8080/hebdev2/
In terminal, run:

```
sudo mongod
```

This starts the mongodb
Once that is finished, navigate to the swagger docs page: http://localhost:8080/hebdev2/api-docs/
Here you can try out the different API functions


## Built With

* [Eclipse](https://www.eclipse.org) - The IDE used
* [Maven](https://maven.apache.org/) - Dependency Management and Build Automation
* [Swagger](https://swagger.io/docs/) - UI Documentation and API Mocking and Virtualization
* [Tomcat](http://tomcat.apache.org/) - Application Server
* [Log4J](https://logging.apache.org/log4j/2.x/) - Logging
* [Jersey](https://jersey.github.io/) - RESTful Web Services in Java
* [MongoDB](https://www.mongodb.com/) - Open Source Document Database
* [JUnit](http://junit.sourceforge.net/junit3.8.1/) - For testing

## Authors

* **Ted Nanney** 
