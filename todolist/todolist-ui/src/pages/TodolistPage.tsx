import { useState } from "react";
import CreateTodolist from "../components/todolists/CreateTodolist";
import Todolists from "../components/todolists/Todolists";
import { Box, SpeedDial, SpeedDialIcon } from '@mui/material'

const TodolistPage = () => {
    const [showCreate, setShowCreate] = useState(false)
    const showSpeedDial = !showCreate

    return <Box>
        <CreateTodolist open={showCreate} hide={() => setShowCreate(false)} />
        <Todolists />
        {showSpeedDial && <SpeedDial
            ariaLabel="SpeedDial basic example"
            sx={{ position: 'absolute', bottom: 16, right: 16 }}
            onClick={() => setShowCreate(true)}
            icon={<SpeedDialIcon />}
        ></SpeedDial>}
    </Box>

}

export default TodolistPage;