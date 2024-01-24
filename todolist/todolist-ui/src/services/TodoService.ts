
export interface TodoService {
    create(todolistId: string, label: string): Promise<void>
}


export default class TodoServiceImpl implements TodoService {

    constructor() {

    }



    public async create(createTodolink: string, label: string): Promise<void> {
        const command = {
            label
        }

        const body = JSON.stringify(command)

        return fetch(createTodolink, {
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
}