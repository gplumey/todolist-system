export type Todolist = {
    id: string | null
    name: string
    todos: Todo[]

}

export type Todo = {
    id: string | null
    label: string
}