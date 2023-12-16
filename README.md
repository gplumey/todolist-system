# todolist-system

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
    RestController --> GetAllTodolistUseCase
    RestController --> GetTodolistUsecase
    RestController --> CreateTodolistUseCase
    RestController --> AddTaskUseCase


    GetAllTodolistUseCase --> Todolist
    GetTodolistUsecase --> Todolist
    CreateTodolistUseCase --> Todolist
    AddTaskUseCase --> Todolist


    GetAllTodolistUseCase --> TodolistReadRepository
    GetTodolistUsecase --> TodolistReadRepository
    CreateTodolistUseCase --> TodolistReadRepository
    AddTaskUseCase --> TodolistReadRepository
    CreateTodolistUseCase --> TodolistWriteRepository
    AddTaskUseCase --> TodolistWriteRepository
    TodolistRepositoryImpl --> TodolistReadRepository
    TodolistRepositoryImpl --> TodolistWriteRepository

    Todolist --> Task
    subgraph app [Application layer]
        RestController
    end
    subgraph service [Service layer]
        subgraph inbound [Inbound]
            GetAllTodolistUseCase
            GetTodolistUsecase
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
