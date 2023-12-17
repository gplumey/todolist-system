# todolist-system

![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

## Introduction

This repository implements a small todolist system in a Clean architecture style.
following the Clean code Principles such as SOLID and DRY, DDD

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
- Restful API (Documented with Swagger)

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
    User --> RestController
    User --> GraphQLController

    RestController --> GetAllTodolistUseCase
    RestController --> GetTodolistUsecase
    RestController --> GetTaskUsecase
    RestController --> CreateTodolistUseCase
    RestController --> AddTaskUseCase

    GraphQLController --> GetAllTodolistUseCase
    GraphQLController --> AddTaskUseCase

    GetAllTodolistUseCase --> Todolist
    GetTodolistUsecase --> Todolist
    CreateTodolistUseCase --> Todolist
    AddTaskUseCase --> Todolist


    GetAllTodolistUseCase --> TodolistReadRepository
    GetTodolistUsecase --> TodolistReadRepository
    GetTaskUsecase --> TodolistReadRepository
    CreateTodolistUseCase --> TodolistReadRepository
    AddTaskUseCase --> TodolistReadRepository
    CreateTodolistUseCase --> TodolistWriteRepository
    AddTaskUseCase --> TodolistWriteRepository
    TodolistRepositoryImpl --> TodolistReadRepository
    TodolistRepositoryImpl --> TodolistWriteRepository

    Todolist --> Task
    subgraph app [Application layer]
        RestController
        GraphQLController
    end
    subgraph service [Service layer]
        subgraph inbound [Inbound]
            GetAllTodolistUseCase
            GetTodolistUsecase
            GetTaskUsecase
            CreateTodolistUseCase
            AddTaskUseCase
        end
        subgraph domain [Aggregates]
            Todolist
            Task
        end
        subgraph outbound [Outbound]
            TodolistReadRepository
            TodolistWriteRepository
        end
    end

    subgraph infra [Infra layer]
        TodolistRepositoryImpl

    end
```

````mermaid
---
title: Class diagram
---
classDiagram
    AggregateRoot <|-- Todolist
    BaseEntity <|-- AggregateRoot
    BaseEntity <|-- Task

    Task "0..100" -- "1" Todolist
    class BaseEntity {
        +id
    }
    class AggregateRoot {

    }
    class Todolist {
        ^+TodolistId id;
        +TodolistName name
        +Task[] tasks
        $create(): Todolist
    }

    class Task {
        ^+TaskId id;
        ^+TodolistId id;
        +TaskLabel label
    }
````

```mermaid
---
title: Sequence diagram
---
sequenceDiagram
    Client ->>+ Todolist: GET /todolist
    Todolist -->>- Client: SUCCESS [Todolist]
    Client ->>+ Todolist: POST /todolist
    Note over Client, Todolist: name:String

    Todolist -->>- Client: SUCCESS Todolist
```

## Query to list todolists

```graphql
query {
    todolists {
        id
        tasks {
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
        "tasks": []
      }
    ]
  }
}
```

## Mutation to add a task

```graphql
mutation {
    addTask (addTaskInput: {
        todolistId: "d4ceb68c-4a5b-4487-bb82-030f9294f7a1"
        label: "graphql task"
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
    "addTask": {
      "id": "36295cc4-c030-41eb-a6b6-e595cc88b9a8",
      "label": "graphql task"
    }
  }
}
```
