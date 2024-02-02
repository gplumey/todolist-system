import { ReactNode, useReducer, useContext, createContext } from 'react'

type AppState = {
    errorMessage: string | null
}

type AppStateContext = AppState & {
    setErrorMessage: (message: string) => void
    clearErrorMessage: () => void
}


const initialState: AppState = {
    errorMessage: null
}

type ACTIONS = 'SET_ERROR_MESSAGE';

type Action = {
    type: ACTIONS
    payload: string | null
}

const reducer = (state: AppState, action: Action): AppState => {
    const { type, payload } = action

    switch (type) {
        case 'SET_ERROR_MESSAGE':
            return { ...state, errorMessage: payload as string ?? null }

    }
    return state
}



type AppStateProviderProps = {
    children: ReactNode | undefined
}
export const AppStateContext = createContext<AppStateContext>({} as AppStateContext)
export const AppStateProvider = ({ children }: AppStateProviderProps) => {

    const [state, dispatch] = useReducer(reducer, initialState)
    function setErrorMessage(message: string) {
        dispatch({ type: 'SET_ERROR_MESSAGE', payload: message })
    }

    function clearErrorMessage() {
        dispatch({ type: 'SET_ERROR_MESSAGE', payload: null })
    }
    const value: AppStateContext = {
        ...state,
        setErrorMessage,
        clearErrorMessage

    }
    return (<AppStateContext.Provider value={value} > {children} </AppStateContext.Provider>)
}

export const useAppState = () => {
    return useContext(AppStateContext)
} 