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
  RestController --> GetAllTodolist
  RestController --> CreateTodolist

  GetAllTodolist --> Todolist
  CreateTodolist --> Todolist
  GetAllTodolist --> TodolistRepository
  CreateTodolist --> TodolistRepository
  TodolistRepository --> db1
  subgraph inbound [Application layer]
    RestController
  end
  subgraph service [Service layer]
    subgraph usecase [Use cases]
      GetAllTodolist
      CreateTodolist
    end
    subgraph domain [Aggregates]
      Todolist
    end
  end

  subgraph outbound [Infra layer]
    TodolistRepository
  end
  db1[(Database)]
```
```mermaid
---
title: Sequence diagram
---
sequenceDiagram
    Client->>+Todolist: GET /todolist
    Todolist-->>-Client: SUCCESS [Todolist]
    Client->>+Todolist: POST /todolist
    Note over Client,Todolist: name:String

  Todolist-->>-Client: SUCCESS Todolist
```

````mermaid
---
title: Class diagram
---
classDiagram
    BaseEntity <|-- AggregateRoot
    AggregateRoot <|-- Todolist

    class BaseEntity {
        +id
    }
    class AggregateRoot {

    }
    class Todolist {
        ^+TodolistId id;
        +TodolistName name
        $create(): Todolist
    }
````