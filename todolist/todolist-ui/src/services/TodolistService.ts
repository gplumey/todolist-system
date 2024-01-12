import { Todolist } from "../states/types"

type TodolistDTO = {
    name: string
}


export default class TodolistService {

    public async getAll(): Promise<Todolist[]> {
        return fetch('http://localhost:8080/todolist', {
            headers: {
                'Content-type': 'application/json'
            }
        })
            .then((res) => res.json())
            .then(json => json.map((item: TodolistDTO) => ({ name: item.name })));
    }

}