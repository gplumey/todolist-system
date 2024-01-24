import { Box, List, ListItemButton, ListItemText } from "@mui/material"
import { useTodos } from "../../hooks/useTodos"


type TodosProps = {
    todolistId: string
}

const Todos = ({ todolistId }: TodosProps) => {
    const { todos } = useTodos(todolistId)
    return <Box>
        <List>
            {todos.map(todo => <ListItemButton key={todo.id} component="a" href="#simple-list">
                <ListItemText primary={todo.label} /></ListItemButton>)}
        </List>
    </Box >
}

export default Todos