
import './App.css'

import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from 'react-router-dom'
import TodolistPage from './pages/TodolistPage'
import { DependencyProvider } from './services/ServiceProvider'
import { QueryClient, QueryClientProvider } from 'react-query'
import TodosPage from './pages/TodosPage'
import RootLayout from './layouts/RootLayout'
import { AppStateProvider } from './hooks/useAppState'

const queryClient = new QueryClient()

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path='/' element={<RootLayout />}>
      <Route index element={<TodolistPage />} />
      <Route path="/todolists/:todolistId" element={<TodosPage />} />
    </Route>
  )
)

function App() {

  return (
    <QueryClientProvider client={queryClient}>
      <DependencyProvider >
        <AppStateProvider>
          <RouterProvider router={router} />
        </AppStateProvider >
      </DependencyProvider>
    </QueryClientProvider>
  )
}

export default App
