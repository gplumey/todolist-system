import { createContext, useContext, ReactNode } from 'react'
import TodolistService from './TodolistService'
import { ServiceFactory } from './ServiceFactory'

export type Services = {
    todolistService: TodolistService
}

const services = {
    todolistService: ServiceFactory.createTodolistService()
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