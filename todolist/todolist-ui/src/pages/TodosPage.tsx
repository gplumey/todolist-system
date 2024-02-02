import { useParams } from "react-router-dom"
import Todos from "../components/todos/Todos";
import { Box, SpeedDial, SpeedDialIcon } from "@mui/material";
import CreateTodo from "../components/todos/CreateTodo";
import { useTodolist } from "../hooks/useTodolist";
import { useEffect, useState } from "react";
import { useAppContext } from "../hooks/useAppContext";


const TodosPage = () => {
    const [showCreate, setShowCreate] = useState(false)
    const showSpeedDial = !showCreate
    const { todolistId } = useParams();
    const { todolist } = useTodolist(todolistId ?? "")
    const { setCurrentTodolist } = useAppContext()
    useEffect(() => {
        todolist && setCurrentTodolist(todolist)
    }, [setCurrentTodolist, todolist])

    return <Box>
        {todolistId && <>
            <CreateTodo todolistId={todolistId} open={showCreate} hide={() => setShowCreate(false)}></CreateTodo>
            <Todos todolistId={todolistId}></Todos>
            {showSpeedDial && <SpeedDial
                ariaLabel="SpeedDial basic example"
                sx={{ position: 'absolute', bottom: 16, right: 16 }}
                onClick={() => setShowCreate(true)}
                icon={<SpeedDialIcon />}
            ></SpeedDial>}
        </>}
    </Box>
}


export default TodosPage