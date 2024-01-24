import { useDependency } from "../services/ServiceProvider"
import { useQuery, useMutation, useQueryClient } from 'react-query'
import { useAppState } from './useAppState'
import { Todolist } from "../types"

const TODOLISTS_QUERY = "todolists";



interface UseCreateTodolist {
    todolists: Todolist[]
    createTodolist: (name: string) => void
    clearErrorMessage: () => void
    refresh: () => void
    isLoading: boolean
    errorMessage: string | null
}

type UseCreateTodolistProps = {
    onCreateSuccess?: () => void
}

export const useTodolists = ({ onCreateSuccess }: UseCreateTodolistProps = {}): UseCreateTodolist => {
    const { todolistService } = useDependency()
    const { data: todolists = [] } = useQuery(TODOLISTS_QUERY, todolistService.getAll)
    const queryClient = useQueryClient()
    const { errorMessage, setErrorMessage, clearErrorMessage } = useAppState()


    const { mutate: createTodolist, isLoading } = useMutation(todolistService.create, {
        onSuccess: () => {
            clearErrorMessage()
            queryClient.invalidateQueries(TODOLISTS_QUERY)
            onCreateSuccess && onCreateSuccess()
        },
        onError: async (error: Error) => {
            const message = '' + (await error)
            setErrorMessage(message)
        }
    })


    const refresh = () => {
        queryClient.invalidateQueries(TODOLISTS_QUERY)
    }

    return { errorMessage, todolists, createTodolist, clearErrorMessage, isLoading, refresh }
}
