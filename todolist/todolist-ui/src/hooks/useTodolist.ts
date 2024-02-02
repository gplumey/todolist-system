import { useDependency } from "../services/ServiceProvider"
import { useQuery } from 'react-query'
import { Todolist } from "../types"
import { TodolistActions } from "../services/TodolistService";

const TODOLIST_QUERY = "todolist";

interface UseCreateTodolist {
    todolist: Todolist | null
    actions?: TodolistActions
    isLoading: boolean
}

export const useTodolist = (todolistId: string): UseCreateTodolist => {
    const { todolistService } = useDependency()
    const { data, isLoading } = useQuery(TODOLIST_QUERY, () => todolistService.getById(todolistId))
    const todolist = data && data[0] || null;
    const actions = data && data[1] || { createTodoLink: null };
    return {
        todolist, actions, isLoading
    }
}
