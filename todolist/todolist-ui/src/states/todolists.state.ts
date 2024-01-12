import { useState, useEffect } from "react";
import { BehaviorSubject } from "rxjs";
import { TodolistStore } from "./types";



const initialState: TodolistStore = {
    data: [],
    isLoading: false,
    isLoaded: false,
    errorMessage: ''
}

const subject: BehaviorSubject<TodolistStore> = new BehaviorSubject<TodolistStore>(initialState)
export const useTodolists = () => {
    const [todolists, setTodolits] = useState(initialState);

    useEffect(() => {
        const subscription = subject.subscribe((newState) => {
            setTodolits(newState)
        })
        load()
        return () => {
            if (subscription) {
                subscription.unsubscribe()
            }
        }
    }, [])


    function load() {
        if (todolists.isLoading) return
        subject.next({ ...todolists, isLoading: true })
        setTimeout(() => {
            subject.next({
                data: [{ name: "default todolist" }],
                isLoading: false,
                isLoaded: true,
                errorMessage: ''
            })
        }, 2000)

    }

    function addTask(name: string) {
        const data = [...todolists.data];
        data.push({ name })
        subject.next({ ...todolists, data })
    }

    return { todolists, addTask }
}