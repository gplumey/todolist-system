export type Todolist = {
    name: string
}

export type TodolistStore = {
    data: Todolist[],
    isLoading: boolean,
    isLoaded: boolean,
    errorMessage: string
}

