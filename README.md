# Customer-Product-Management
This is a Spring Boot Application that provides API Endpoints to manage Customers and Products which demonstrates POST, GET, PUT, and DELETE
___
# System DB design and General coding design
Erd
------
    erDiagram
    CUSTOMER ||--o{ ORDERS : places
    ORDERS ||--|{ ORDER-HISTORY : have
    ORDERS ||--|{ ORDER-ITEM : includes
    PRODUCTS ||--|| ORDER-ITEM : "ordered in"
    PRODUCTS ||--|{ PRODUCT-HISTORY : have
- this is the ERD design for this spring boot
- in this spring boot app, TiDB (MySQL) run on Docker is used
- this DB mainly have the function of recording customer, product, order, order item
- to have a better view in diagram, kindly move to [Mermaid.live](mermaid.live), and paste the code above to view it
___
# Setup and Dependecies
Dependecies
---------
- Started with [Spring Initializr](start.spring.io) with following dependencies :
    - Spring Boot DevTools, Spring Configuration Processor, Lombok    [Developer Tools]
    - Spring Web    [Web]
    - Spring Data JPA    [SQL]
    - Spring Boot Actuator    [OPS]
    - Spring Rest Docs [Testing]

Prerequisite Software
---------
- Have Docker Desktop, Git, Your IDE, database tool, and Postman API

Setup
---------
1. Locate **docker-compose.yml** *this will create a TiDB container*
2. At current path, run *docker-compose up -d* in cmd prompt to start up docker
3. Locate **root > sql > V0__Initialize_table.sql**, paste the script prepared into database tool to insert mock data
4. Run *CustomerProductManagementApplication* to start up Spring Boot
5. Done
___
# API ready to run
> Product WebService
    - Product Update with pid [PUT], Product Delete with pid [DELTE]

> Customer WebService
    - Retrieve Customer List [POST], Get Order Detail with Customer Id [POST], count total customer [GET]
___
