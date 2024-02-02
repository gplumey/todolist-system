import TodoServiceImpl from "./TodoService";
import TodoService from "./TodoService";
import TodolistServiceImpl from "./TodolistService";
import TodolistService from "./TodolistService";




export class ServiceFactory {

    static createTodolistService(): TodolistService {
        return new TodolistServiceImpl()
    }

    static createTodoService(): TodoService {
        return new TodoServiceImpl()
    }

}