import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Todolists from './components/todolists/Todolists'
import Todolists2 from './components/todolists/Todolists2'
import Todolists3 from './components/todolists/Todolists3'
import { DependencyProvider } from './services/ServiceProvider'
import { QueryClient, QueryClientProvider, useQuery } from 'react-query'

const queryClient = new QueryClient()

function App() {
  const [count, setCount] = useState(0)

  return (
    <QueryClientProvider client={queryClient}>
      <DependencyProvider >


        <Todolists />
        <Todolists2 />
        <Todolists3 />
        <div>
          <img src={viteLogo} className="logo" alt="Vite logo" />

          <img src={reactLogo} className="logo react" alt="React logo" />
        </div>
        <h1>Vite + React</h1>
        <div className="card">
          <button onClick={() => setCount((count) => count + 1)}>
            count is {count}
          </button>
          <p>
            Edit <code>src/App.tsx</code> and save to test HMR
          </p>
        </div>
        <p className="read-the-docs">
          Click on the Vite and React logos to learn more
        </p>
      </DependencyProvider>
    </QueryClientProvider>
  )
}

export default App
