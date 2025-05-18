# Sleep Noom Challenge

API to record sleep, record when you wake up, record how you felt when you woke up and return the averages of the last 30 days

## Project technical specifications

### 📋 Ferramentas utilizadas

* Java 
* Gradle
* Docker-compose
* PostgreSQL
* Flyway
* Swagger
* JUnit
* Mockito


## 🔧 Steps to implement the project

Run the following command in the project's sleep directory
to upload the database:
```
docker-compose up
```
After verifying that the bank is working, build the project with Gradle:
```
gradle clean install
```
In order for the database tables to be created, you need to run the following command:
```
flyway:migrate
```
To view the endpoint documentation, with the application running, access it in the browser:
```
http://localhost:8080/swagger-ui/index.html
```

## ⚙️ Running the endpoints

The project root directory contains a collection to import into postman.