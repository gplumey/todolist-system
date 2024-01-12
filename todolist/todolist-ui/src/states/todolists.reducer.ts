import { useReducer, useEffect } from "react";
import { Todolist, TodolistStore } from "./types";


const initialState: TodolistStore = {
    data: [],
    isLoading: false,
    isLoaded: false,
    errorMessage: ''
}

enum ACTIONS {
    LOAD = "LOAD",
    ADD = "ADD",
    SET = "SET"
}



type Action = {
    type: ACTIONS;
    payload: Todolist | Todolist[] | undefined
}

const reducer = (state: TodolistStore, action: Action): TodolistStore => {
    const { type, payload } = action;

    switch (type) {
        case ACTIONS.ADD:
            if (payload) {
                const data = [...state.data]
                data.push(payload as Todolist)
                return { ...state, data }
            }
            break
        case ACTIONS.LOAD:
            return { ...state, isLoading: true }
        case ACTIONS.SET:
            return { ...state, isLoading: false, isLoaded: true, data: [...payload as Todolist[]] }
    }
    return state;
}

export const useTodolists2 = (): [
    TodolistStore,
    {
        addTask: (name: string) => void
    }
] => {
    const [state, dispatch] = useReducer(reducer, initialState);

    useEffect(() => {
        console.log("LOAD")
        load()
    }, [])

    function load() {
        if (state.isLoading) return
        dispatch({ type: ACTIONS.LOAD, payload: undefined })
        setTimeout(() => {
            dispatch({ type: ACTIONS.SET, payload: [{ name: "default todolist" }] })
        }, 2000)

    }

    function addTask(name: string) {
        dispatch({ type: ACTIONS.ADD, payload: { name } })
    }

    return [state, { addTask }];
}