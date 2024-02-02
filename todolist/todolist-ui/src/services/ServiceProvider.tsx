import { createContext, useContext, ReactNode } from 'react'
import TodolistService from './TodolistService'
import { ServiceFactory } from './ServiceFactory'
import TodoService from './TodoService'

export type Services = {
    todolistService: TodolistService
    todoService: TodoService
}

const services = {
    todolistService: ServiceFactory.createTodolistService(),
    todoService: ServiceFactory.createTodoService()
}

const DependencyContext = createContext<Services>(services)

type DependencyProviderProps = {
    children: ReactNode | undefined
}

export function DependencyProvider({ children }: DependencyProviderProps) {
    return <DependencyContext.Provider value={services} > {children} </DependencyContext.Provider>
}

export function useDependency() {
    return useContext(DependencyContext)
}