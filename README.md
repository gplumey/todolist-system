[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/guillaume-plumey)

# Badges

![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

## Introduction

This repository is a sample of todolist system in a **Clean architecture** style.
Following Clean code Principles.

- **KISS**: Keep It Simple Stupid.
- **DRY**: Don't Repeat Yourself
- **YAGNI**: You Aren't Gonna Need It
- **SOLID** Design Principles?
    - **S**ingle Responsibility Principle (SRP)
    - **O**pen-Closed Principle (OCP)
    - **L**iskov Substitution Principle (LSP)
    - **I**nterface Segregation Principle (ISP)
    - **D**ependency Inversion Principle (DIP)

And some design pattern

- **DDD** : Domain driven Design
- **CQS** : Command–Query Separation

And the Test-Driven Development methodology.

# 5 Whys

- **Why** Choosing To-Do list for this Knowledge transfer ?
    - First, the To-Do list domain is simple and well know domain by developers. But is also domain that can be included
      as a microservice in many systems.

      For instance, on a customer onboarding flow, we can propose a To-Do list to the
      customer in order to helper him to
      complete his profile.
- **Why** Clean Architecture ?
    - Clean Architecture is a software architecture pattern that promotes the development of loosely coupled components
      that are decoupled from the underlying framework. This helps to improve maintainability, modularity, and
      testability, as well as reduce development time and cost
        - **Why** testability is a important?
            - Unit testing is a crucial part of software development as it helps catch defects early in the process,
              reduce
              costs, improve code quality, and facilitate refactoring.
        - **Why** loosely coupling is better than tight coupling?
            - By decoupling the components of an application or an architecture, it becomes much easier to change
              configuration than it is with tight coupling.
        - **Why** not using the traditional N-Layer architecture
            - Even if a To-Do list has limited set of business logic, it aims to be integrated in a wilder system by
              using several inbound and outbound technology (Rest, GraphQl, gRPC, Sql DB, noSql DB, caching,event
              publication and subscription). Decoupling all these Frameworks and Drivers ease this maintainability of
              the system and simplify the code in the business logic.
- **Why** writing clean code
    - Clean code is a set of programming practices that emphasize the readability, maintainability, and simplicity of
      code. Writing clean code is essential because it helps developers to understand and modify code more efficiently,
      which can save time and reduce the risk of introducing errors.
- **Why** Domain Driven Design (DDD)?
    - DDD enables:
        1. Improved communication: The ubiquitous language allows developers and business experts
           to collaborate more efficiently.
        2. Business alignment: The software design directly reflects real business processes
           and goals.
        3. Domain event programming: Domain events help separate concerns of components, reducing dependencies and
           promoting modularity.
- **Why** Command–Query Separation ?
    - CQS enables :
        1. Separation of concerns: Promotes a clear separation between actions that change the system and operations
           that retrieve information, improving code organization and maintainability.
        2. Improved Code Clarity: Separating actions from queries leads to cleaner, more readable code, as it clarifies
           the intent and purpose of each method.
        3. Testability: By separating commands and queries, it becomes easier to write unit tests that validate the
           behavior of each type of method independently.

# Technical stack

- Java 17
- Spring-boot 3
- Maven
- API : GraphQL

> POST http://[host]:[port]/graphql

> GraphiQL : http://[host]:[port]/graphiql

- Restful API Level 3
    - Hypermedia Application Language (Hateoas)
        - **GET** http://[host]:[port]/todolist
        - **POST** http://[host]:[port]/todolist
        - **GET** http://[host]:[port]/todolist/{todolistId}
        - **POST** http://[host]:[port]/todolist/{todolistId}/todo
    - OpenAPI documentation (Swagger)

> Documentation : http://[host]:[port]/swagger-ui

- Continuous Integration with github actions
- Observability: Actuator and micrometer
- Unit testing: Junit 5 and Mockito
- Acceptance tests: Cucumber test with Gherkin language

# Coming soon :

- More explanations is this README
- Persistence in a Database
- Security
    - Oauth2 with JWT
    - Access control
- Domain events (pub/sub)
- Caching with Redis
- Reactive API with Flux
- Continuous Delivery with github actions (maybe even Continuous Deployment)

## Diagrams

```mermaid
---
title: Clean Architecture
---
flowchart
    User((User))
    ConcurrentHashMap[(ConcurrentHashMap)]
    User --> RestController
    User --> GraphQLController
    RestController --> *Query*UseCase
    RestController --> *Command*Usecase
    GraphQLController --> *Query*UseCase
    GraphQLController --> *Command*Usecase
    *Query*UseCase --> Todolist
    *Command*Usecase --> Todolist
    *Query*UseCase --> TodolistReadRepository
    *Command*Usecase --> TodolistReadRepository
    *Command*Usecase --> TodolistWriteRepository
    TodolistRepositoryImpl --> TodolistReadRepository
    TodolistRepositoryImpl --> TodolistWriteRepository
    Todolist --> Todo
    TodolistRepositoryImpl --> ConcurrentHashMap
    subgraph app [Application layer]
        RestController
        GraphQLController
    end
    subgraph service [Service layer]
        subgraph domain [Aggregates]
            Todolist
            Todo
        end
        subgraph inbound [Inbound]
            *Query*UseCase
            *Command*Usecase
        end
        subgraph outbound [Outbound]
            TodolistReadRepository
            TodolistWriteRepository
        end
    end

    subgraph infra [Infra layer]
        TodolistRepositoryImpl
        ConcurrentHashMap
    end
```

```mermaid
flowchart LR
    Representation[Representation layer]
    Representation --> GetAllTodolistUseCase
    Representation --> GetTodolistUsecase
    Representation --> GetTodoUsecase
    Representation --> CreateTodolistUseCase
    Representation --> CreateTodoUseCase
    subgraph infra [Usecases]
        subgraph queries [Queries]
            GetAllTodolistUseCase
            GetTodolistUsecase
            GetTodoUsecase
        end
        subgraph commands [Commands]
            CreateTodolistUseCase
            CreateTodoUseCase
        end
    end
```

# Modules dependency graph

![Branches](readme-images/dependency-graph.png)

Generate command:
> mvn clean depgraph:aggregate*

# Class diagram

````mermaid
classDiagram
    AggregateRoot <|-- Todolist
    BaseEntity <|-- AggregateRoot
    BaseEntity <|-- Todo
    Todo "0..100" -- "1" Todolist
    class BaseEntity {
        +id
    }
    class AggregateRoot {
    }
    class Todolist {
        ^+TodolistId id;
        +TodolistName name
        +Todo[] todos
        $create(): Todolist
    }

    class Todo {
        ^+TodoId id;
        ^+TodolistId id;
        +TodoLabel label
    }
````

# Sequence diagrams

```mermaid
---
title: Sequence diagram for GetAllTodolist Usecase
---
sequenceDiagram
    Client ->>+ TodolistRestController: GET /todolist
    TodolistRestController ->>+ GetAllTodolistUseCase: request
    GetAllTodolistUseCase ->>+ TodolistReadRepository: findAll
    TodolistReadRepository ->>+ TodolistReadRepositoryImpl: findAll
    TodolistReadRepositoryImpl -->>- TodolistReadRepository: [Todolist]
    TodolistReadRepository -->>- GetAllTodolistUseCase: [Todolist]
    GetAllTodolistUseCase -->>- TodolistRestController: [Todolist]
    TodolistRestController -->>- Client: SUCCESS [TodolistDto]

```

# GraphQL examples

## Query ***todolists***

Query:

```graphql
query {
    todolists {
        id
        todos {
            id
            label
        }
    }
}

```

Response :

```json
{
  "data": {
    "todolists": [
      {
        "id": "dd657c98-eac1-45a0-8216-45febcce37b2",
        "todos": []
      }
    ]
  }
}
```

## Mutation ***createTodo***

Query :

```graphql
mutation {
    createTodo (createTodoInput: {
        todolistId: "d4ceb68c-4a5b-4487-bb82-030f9294f7a1"
        label: "graphql todo"
    }) {
        id
        label
    }
}
```

Response :

```json
{
  "data": {
    "createTodo": {
      "id": "36295cc4-c030-41eb-a6b6-e595cc88b9a8",
      "label": "graphql todo"
    }
  }
}
```

Restful

````
GET http://localhost:8080/todolist/e9cc947c-bc45-4376-8706-ead00dc5d855
````

````json
{
  "id": "e9cc947c-bc45-4376-8706-ead00dc5d855",
  "name": "first todolist",
  "todos": [
    {
      "id": "24cc7be4-0548-402d-bf7f-3eb5c1389beb",
      "label": "new todo",
      "_links": {
        "self": {
          "href": "http://localhost:8080/todolist/e9cc947c-bc45-4376-8706-ead00dc5d855/todo/todo/24cc7be4-0548-402d-bf7f-3eb5c1389beb"
        },
        "todolist": {
          "href": "http://localhost:8080/todolist/e9cc947c-bc45-4376-8706-ead00dc5d855"
        }
      }
    }
  ],
  "_links": {
    "self": {
      "href": "http://localhost:8080/todolist/e9cc947c-bc45-4376-8706-ead00dc5d855"
    },
    "add-task": {
      "href": "http://localhost:8080/todolist/e9cc947c-bc45-4376-8706-ead00dc5d855/todo"
    }
  }
}
````

# Development environment setup

## Environement variables

> JAVA_HOME
