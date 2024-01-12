import TodolistService from "./TodolistService";

export class ServiceFactory {

    static createTodolistService(): TodolistService {
        return new TodolistService()
    }
}