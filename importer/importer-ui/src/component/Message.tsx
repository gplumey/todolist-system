import { useState } from "react";
import { useSubscription } from "react-stomp-hooks";


const Message = () => {
    const [lastMessage, setLastMessage] = useState("No message received yet");

    useSubscription("/topic/message", (message) => setLastMessage(message.body));

    return (
        <>Message : {lastMessage}</>
    )
}

export default Message