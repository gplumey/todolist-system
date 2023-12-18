# Badges

![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

## Introduction

This repository is a sample of todolist system in a Clean architecture style.
Following Clean code Principles.

- KISS: Keep It Simple Stupid.
- DRY: Don't Repeat Yourself
- YAGNI: You Aren't Gonna Need It
- SOLID Design Principles?
    - Single Responsibility Principle (SRP)
    - Open-Closed Principle (OCP)
    - Liskov Substitution Principle (LSP)
    - Interface Segregation Principle (ISP)
    - Dependency Inversion Principle (DIP)

And some design pattern

- DDD : Domain driven Design
- CQS : Command–Query Separation

# Technical stack

- Java 17
- Spring-boot 3
- GraphQL
- Restful API
    - Hypermedia Application Language (Hateoas)
    - OpenAPI documentation (Swagger)
- Continuous Integration with github actions

# Coming soon :

- More explanations is this README
- Persistence in a Database
- Security
    - Oauth2 with JWT
    - Access control
- Domain events (pub/sub)
- Observability (Actuator and sleuth)
- Caching with Redis
- Reactive API with Flux
- Continuous Delivery with github actions (maybe even Continuous Deployment)

```
 http:[host]:[port]/swagger-ui
```

## Diagrams

```mermaid
---
title: Clean Architecture
---
flowchart LR

    User((User))
    ConcurrentHashMap[(ConcurrentHashMap)]
    User --> RestController
    User --> GraphQLController

    RestController --> GetAllTodolistUseCase
    RestController --> GetTodolistUsecase
    RestController --> GetTodoUsecase
    RestController --> CreateTodolistUseCase
    RestController --> AddTodoUseCase

    GraphQLController --> GetAllTodolistUseCase
    GraphQLController --> AddTodoUseCase

    GetAllTodolistUseCase --> Todolist
    GetTodolistUsecase --> Todolist
    CreateTodolistUseCase --> Todolist
    AddTodoUseCase --> Todolist


    GetAllTodolistUseCase --> TodolistReadRepository
    GetTodolistUsecase --> TodolistReadRepository
    GetTodoUsecase --> TodolistReadRepository
    CreateTodolistUseCase --> TodolistReadRepository
    AddTodoUseCase --> TodolistReadRepository
    CreateTodolistUseCase --> TodolistWriteRepository
    AddTodoUseCase --> TodolistWriteRepository
    TodolistRepositoryImpl --> TodolistReadRepository
    TodolistRepositoryImpl --> TodolistWriteRepository

    Todolist --> Todo

    TodolistRepositoryImpl --> ConcurrentHashMap
    subgraph app [Application layer]
        RestController
        GraphQLController
    end
    subgraph service [Service layer]
        subgraph inbound [Inbound]
            GetAllTodolistUseCase
            GetTodolistUsecase
            GetTodoUsecase
            CreateTodolistUseCase
            AddTodoUseCase
        end
        subgraph domain [Aggregates]
            Todolist
            Todo
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

````mermaid
---
title: Class diagram
---
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

## Query to list todolists

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

- Response

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

## Mutation to add a todo

```graphql
mutation {
    addTodo (addTodoInput: {
        todolistId: "d4ceb68c-4a5b-4487-bb82-030f9294f7a1"
        label: "graphql todo"
    }) {
        id
        label
    }
}
```

- Response

```json
{
  "data": {
    "addTodo": {
      "id": "36295cc4-c030-41eb-a6b6-e595cc88b9a8",
      "label": "graphql todo"
    }
  }
}
```
