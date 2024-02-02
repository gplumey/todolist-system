
import { test } from 'vitest'
import { render } from '@testing-library/react'
import CreateTodolist from './CreateTodolist'
import { QueryClient } from 'react-query'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 2,
    },
  },
})

// ✅ only todos will retry 5 times
queryClient.setQueryDefaults(['todos'], { retry: 5 })



test('Renders CreateTodolist', () => {
  render(<CreateTodolist open={true} hide={() => { }} />)
})