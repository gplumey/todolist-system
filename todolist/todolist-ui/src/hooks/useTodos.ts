import { useMutation, useQuery, useQueryClient } from "react-query"
import { Todo } from "../types"
import { useDependency } from "../services/ServiceProvider"
import { useAppState } from "./useAppState"


type CreateTodoParams = {
    label: string
}
interface UseTodos {
    todos: Todo[]
    isLoading: boolean
    createTodo(label: string): void
}

type UseCreateTodoProps = {
    onCreateSuccess?: () => void
}
export const useTodos = (todolistId: string, { onCreateSuccess }: UseCreateTodoProps = {}): UseTodos => {
    const { todolistService, todoService } = useDependency()
    const { data, isLoading: fetchLoading } = useQuery("todos", () => todolistService.getById(todolistId))
    const queryClient = useQueryClient()
    const { setErrorMessage, clearErrorMessage } = useAppState()
    const todos = data && data[0].todos || []
    const mutation = useMutation(async ({ label }: CreateTodoParams) => {

        const link = data && data[1].createTodoLink;
        if (!link) throw "link not found"
        return todoService.create(link, label)
    }
        , {
            onSuccess: () => {
                clearErrorMessage()
                queryClient.invalidateQueries("todos")
                onCreateSuccess && onCreateSuccess()
            },
            onError: async (error: Error) => {
                const message = '' + (await error)
                setErrorMessage(message)
            }
        })




    return {
        todos,
        isLoading: mutation.isLoading || fetchLoading,
        createTodo: (label: string) => {
            mutation.mutate({ label })
        }
    }
} 