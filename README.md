# todolist-system


## Introduction 
This repository implements a small todolist system in a Hexagonal architecture style.
following some Principles such as SOLID and DRY 

What are SOLID Design Principles?
- Single Responsibility Principle (SRP)
- Open-Closed Principle (OCP)
- Liskov Substitution Principle (LSP)
- Interface Segregation Principle (ISP)
- Dependency Inversion Principle (DIP)


# Technical stack
- Java 17
- Spring-boot 3


## Diagrams
```mermaid
---
title: Use cases
---
graph LR
    
    User[Anonymous user] --> ListTodolistUsecase
    subgraph ide1 [Use cases]
        ListTodolistUsecase
    end
```
```mermaid
---
title: Sequence diagram
---
sequenceDiagram
    Client->>+Todolist: GET /todolist
    Todolist-->>+Client: SUCCESS [todolist]

```

````mermaid
---
title: Class diagram
---
classDiagram
    class Todolist{
        +String name
    }
````