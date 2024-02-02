import './App.css'

import { StompSessionProvider } from 'react-stomp-hooks';
import Message from './component/Message';
import Importer from './component/Importer';

const SOCKET_URL = 'http://localhost:8081/ws';


function App() {


  return (
    <>  <h1>Importer</h1>
      <StompSessionProvider
        url={SOCKET_URL}
      //All options supported by @stomp/stompjs can be used here
      >
        <Importer />
        <Message></Message>
      </StompSessionProvider>
    </>
  )
}

export default App
