import { Todolist } from "../types"

export type CreateTodolistDTO = {
    name: string
}

type TodolistDTO = {
    id: string
    name: string
    todos: TodoDTO[]
    _links: {
        [key: string]: { href: string }
    }[]
}

type TodoDTO = {
    id: string
    label: string
}


export type TodolistActions = {
    createTodoLink: string | null
}

export interface TodolistService {
    getAll(): Promise<Todolist[]>
    getById(todolistId: string): Promise<[Todolist, TodolistActions]>
    create(name: string): Promise<void>
}


export default class TodolistServiceImpl implements TodolistService {
    public async getAll(): Promise<Todolist[]> {
        return fetch('http://localhost:8080/todolist', {
            headers: {
                'Content-type': 'application/json'
            }
        })
            .then((res) => {
                if (res.ok) return res.json()
                else Promise.reject(res.json().then(json => json.detail))
            })
            .then(json => json.map((item: TodolistDTO) => ({
                name: item.name,
                id: item.id
            })));
    }

    public async getById(todolistId: string): Promise<[Todolist, TodolistActions]> {
        return fetch('http://localhost:8080/todolist/' + todolistId, {
            headers: {
                'Content-type': 'application/json'
            }
        })
            .then((res) => {
                if (res.ok) return res.json()
                else Promise.reject(res.json().then(json => json.detail))
            })
            .then(todolist => ([{
                name: todolist.name,
                id: todolist.id,
                todos: todolist?.todos?.map((todo: TodoDTO) => ({ label: todo.label, id: todo.id }) ?? [])
            }, {
                createTodoLink: todolist._links['add-task'].href
            }]));
    }

    public async create(name: string): Promise<void> {
        const dto: CreateTodolistDTO = {
            name: name
        }
        const body = JSON.stringify(dto)
        return fetch('http://localhost:8080/todolist', {
            method: 'POST',
            body,
            headers: {
                'Content-type': 'application/json'
            }
        })
            .then((res) => {
                if (res.ok) return res.json()
                else return Promise.reject(res.json().then(json => json.detail))
            })
    }

    public async delete(todolistId: string): Promise<void> {
        return fetch(`http://localhost:8080/todolist/${todolistId}`, {
            method: 'DELETE',
            headers: {
                'Content-type': 'application/json'
            }
        })
            .then((res) => {
                if (res.ok) return
                else return Promise.reject(res.json().then(json => json.detail))
            })
    }

}