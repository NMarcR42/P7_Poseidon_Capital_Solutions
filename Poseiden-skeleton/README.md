# spring-boot
##Description

This project is a demo Spring Boot application designed to practice and demonstrate CRUD operations, authentication and authorization using Spring Security, as well as best practices in API security and backend development.

##features
-CRUD operations on entities (BidList, Trade, CurvePoint, Rating, RuleName, User)

-Authentication with Spring Security (session-based login)
-Authorization with roles (USER and ADMIN)
-Password encryption with BCrypt
-Validation of user inputs (JSR-303 annotations)
-Error handling and custom error page
-Thymeleaf templates with Bootstrap for UI

## Technical:
1. Spring Boot 3.1.0
2. Java 17
3. Thymeleaf
4. Bootstrap v.4.3.1


## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources
    - Static: src/main/resource/static
4. Create database with name "demo" as configuration in application.properties
5. Run sql script to create table doc/data.sql

## Implement a Feature
1. Create mapping domain class and place in package com.nnk.springboot.domain
2. Create repository class and place in package com.nnk.springboot.repositories
3. Create controller class and place in package com.nnk.springboot.controllers

## Security
1. Create user service to load user from  database and place in package com.nnk.springboot.services
2. Add configuration class and place in package com.nnk.springboot.config


